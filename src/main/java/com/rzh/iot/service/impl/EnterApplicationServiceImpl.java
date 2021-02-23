package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.EnterApplicationDao;
import com.rzh.iot.model.ApprovalOpinion;
import com.rzh.iot.model.EnterApplication;
import com.rzh.iot.model.Message;
import com.rzh.iot.service.ApprovalOpinionService;
import com.rzh.iot.service.EnterApplicationService;
import com.rzh.iot.service.MessageService;
import com.rzh.iot.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class EnterApplicationServiceImpl implements EnterApplicationService {

    @Autowired
    EnterApplicationDao enterApplicationDao;

    @Autowired
    Common common;

    @Autowired
    ApprovalOpinionService approvalOpinionService;

    @Autowired
    MessageService messageService;


    @Override
    public JSONObject getEnterApplicationTableData(Integer limit, Integer currentPage, String status) {
        JSONObject object = new JSONObject();
        int size = enterApplicationDao.getSizeOfEnterApplicationTableData(status);
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",enterApplicationDao.getEnterApplicationTableData(limit,(currentPage - 1) * limit, status));
        return object;
    }

    @Override
    public JSONObject getEnterApplicationDetailData(Long formId) {
        JSONObject object = new JSONObject();
        if (enterApplicationDao.isEnterApplicationExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该记录不存在");
            return object;
        }
        object.put("responseCode",200);
        object.put("enterApplication",enterApplicationDao.getEnterApplicationByFormId(formId));
        return object;
    }

    /**
     * 保存入驻申请
     * */
    @Override
    public JSONObject saveEnterApplication(EnterApplication enterApplication) {
        JSONObject object = new JSONObject();
        if (enterApplication.getFormId() == null){
            /**
             * 新增
             * */
            if (enterApplication.getStatus() != "已发"){
                enterApplication.setStatus("待发");
            }
            enterApplication.setFormName(common.mountFormName(enterApplication.getFormName(),"入驻申请"));
            enterApplicationDao.createEnterApplication(enterApplication);
        }else {
            /**
             * 更新
             * */
            if (enterApplicationDao.isEnterApplicationExist(enterApplication.getFormId()) == 0){
                object.put("responseCode",400);
                object.put("msg","该记录不存在");
                return object;
            }
            enterApplicationDao.updateEnterApplication(enterApplication);
        }
        object.put("responseCode",200);
        object.put("msg", "保存成功");
        return object;
    }

    /**
     * 送办入驻申请
     * */
    @Override
    public JSONObject sendEnterApplication(EnterApplication enterApplication) {
        enterApplication.setStatus("已发");
        JSONObject object = saveEnterApplication(enterApplication);
        if ((int) object.get("responseCode") == 400){
            return object;
        }

        /**
         * 校验
         * */
        if (enterApplicationDao.isEnterApplicationExist(enterApplication.getFormId()) == 0){
            object.put("responseCode",400);
            object.put("msg","该记录不存在");
            return object;
        }

        /**
         * 根据审批流创建审批节点
         * */
        HashMap<String, Object> map = approvalOpinionService.createApprovalOpinions(enterApplication.getFormId(),"入驻申请","新签");

        /**
         * 若无审批流，则默认审批完成
         * */
        if ((int) map.get("responseCode") == 404){
            enterApplicationDao.updateEnterApplicationApprovalStatusByFormId(enterApplication.getFormId(),"审批完成");
            object.put("responseCode",200);
            object.put("msg","送办成功");
            return object;
        }

        /**
         * 获取审批流里首个待审批节点
         * */
        ApprovalOpinion approvalOpinion = approvalOpinionService.getFirstWaitingApprovalOpinion(enterApplication.getFormId(),"入驻申请");
        if (approvalOpinion == null){
            /**
             * 若无审批节点，则视为审批完成
             * */
            enterApplicationDao.updateEnterApplicationApprovalStatusByFormId(enterApplication.getFormId(),"审批完成");
            object.put("responseCode",200);
            object.put("msg","送办成功");
            return object;
        }
        /**
         * 更新审批状态
         * */
        enterApplicationDao.updateEnterApplicationApprovalStatusByFormId(enterApplication.getFormId(),"等待" + approvalOpinion.getApprovalProcessNodeName());
        /**
         * 生成消息
         * */
        Message message = new Message();
        message.setFormId(enterApplication.getFormId());
        message.setMessage(enterApplication.getFormName() + "-" + approvalOpinion.getApprovalProcessNodeName());
        message.setApplicant(enterApplication.getPrincipal());
        message.setPositionId(approvalOpinion.getPositionId());
        message.setType("待办");
        message.setStatus("正常");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        message.setCreateDate(format.format(new Date()));
        message.setContractType("入驻申请");
        /**
         * 更新消息
         * */
        HashMap<String,Object> map1 = messageService.updateMessage(message);
        if ((int) map1.get("responseCode") == 400){
            object.put("responseCode",map1.get("responseCode"));
            object.put("msg",map1.get("msg"));
            return object;
        }
        object.put("responseCode",200);
        object.put("msg","送办成功");
        return object;
    }
}