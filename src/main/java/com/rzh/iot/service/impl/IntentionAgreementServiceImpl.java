package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.IntentionAgreementDao;
import com.rzh.iot.model.ApprovalOpinion;
import com.rzh.iot.model.IntentionAgreement;
import com.rzh.iot.model.Message;
import com.rzh.iot.model.Space;
import com.rzh.iot.service.ApprovalOpinionService;
import com.rzh.iot.service.IntentionAgreementRoomService;
import com.rzh.iot.service.IntentionAgreementService;
import com.rzh.iot.service.MessageService;
import com.rzh.iot.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class IntentionAgreementServiceImpl implements IntentionAgreementService {

    @Autowired
    IntentionAgreementDao intentionAgreementDao;

    @Autowired
    Common common;

    @Autowired
    ApprovalOpinionService approvalOpinionService;

    @Autowired
    MessageService messageService;

    @Autowired
    IntentionAgreementRoomService intentionAgreementRoomService;

    @Override
    public JSONObject getIntentionAgreementTableData(Integer currentPage, Integer limit, String status) {
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        int size = intentionAgreementDao.getSizeOfIntentionAgreementTable(status);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",intentionAgreementDao.getIntentionAgreementTableData((currentPage - 1) * limit, limit, status));
        return object;
    }

    @Override
    public JSONObject saveIntentionAgreementForm(IntentionAgreement intentionAgreement) {
        JSONObject object = new JSONObject();
        if (intentionAgreement.getFormId() == null){
            /**
             * 新增
             * */
            if (intentionAgreement.getStatus() != "已发"){
                intentionAgreement.setStatus("待发");
            }
            intentionAgreement.setResetFlag(new Integer(0));
            intentionAgreement.setFormName(common.mountFormName(intentionAgreement.getFormName(),"意向协议"));
            intentionAgreementDao.createIntentionAgreement(intentionAgreement);
        }else {
            /**
             * 更新
             * */
            if (intentionAgreementDao.isFormExist(intentionAgreement.getFormId()) == 0){
                object.put("responseCode",400);
                object.put("msg","该协议不存在");
                return object;
            }
            intentionAgreementDao.updateIntentionAgreement(intentionAgreement);
        }
        object = intentionAgreementRoomService.updateIntentionAgreementRooms(intentionAgreement.getFormId(),intentionAgreement.getSpaces());
        if ((int) object.get("responseCode") == 400){
            return object;
        }
        object.put("responseCode",200);
        object.put("msg","保存成功");
        return object;
    }

    @Override
    public JSONObject sendIntentionAgreementFrom(IntentionAgreement intentionAgreement) {
        /**
         * 更新/新增意向协议
         * */
        intentionAgreement.setStatus("已发");
        JSONObject object = saveIntentionAgreementForm(intentionAgreement);
        if ((int)object.get("responseCode") == 400){
            return object;
        }
        /**
         * 校验
         * */
        if (intentionAgreementDao.isFormExist(intentionAgreement.getFormId()) == 0){
            object.put("responseCode",400);
            object.put("msg","该意向登记不存在");
            return object;
        }
        /**
         * 更新协议状态
         * */
        intentionAgreementDao.updateIntentionAgreementStatus(intentionAgreement.getFormId(),"已发");
        /**
         * 根据审批流创建节点
         * */
        HashMap<String,Object> map = approvalOpinionService.createApprovalOpinions(intentionAgreement.getFormId(),"意向协议","新签");

        /**
         * 若无启用审批流，则默认审批完成
         * */
        if ((int)map.get("responseCode") == 404) {
            return completeIntentionApproval(intentionAgreement, object);
        }

        /**
         * 获取审批流里首个待审批节点
         * */
        ApprovalOpinion approvalOpinion = approvalOpinionService.getFirstWaitingApprovalOpinion(intentionAgreement.getFormId(),"意向协议");
        if(approvalOpinion == null){
            /**
             * 若无审批节点，则视为审批完成
             * */
            return completeIntentionApproval(intentionAgreement, object);
        }
        intentionAgreementDao.updateIntentionAgreementApprovalStatus(intentionAgreement.getFormId(),"等待"+ approvalOpinion.getApprovalProcessNodeName());
        Message message = new Message();
        message.setFormId(intentionAgreement.getFormId());
        message.setMessage(intentionAgreement.getFormName() + "-" + approvalOpinion.getApprovalProcessNodeName());
        message.setApplicant(intentionAgreement.getApplicant());
        message.setPositionId(approvalOpinion.getPositionId());
        message.setType("待办");
        message.setStatus("正常");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        message.setCreateDate(format.format(new Date()));
        message.setContractType("意向协议");

        HashMap<String,Object> map1 = messageService.updateMessage(message);
        if ((int)map1.get("responseCode") == 400){
            object.put("responseCode",map1.get("responseCode"));
            object.put("msg",map1.get("msg"));
            return object;
        }
        object.put("responseCode",200);
        object.put("msg","送办成功");
        return object;
    }

    private JSONObject completeIntentionApproval(IntentionAgreement intentionAgreement, JSONObject object) {
        intentionAgreementDao.updateIntentionAgreementApprovalStatus(intentionAgreement.getFormId(),"审批完成");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date deadline = format.parse(intentionAgreement.getDeadline());
            if (deadline.getTime() >= format.parse(format.format(new Date())).getTime()){
                List<Space> spaces = intentionAgreementRoomService.getSpacesByIntentionAgreement(intentionAgreement.getFormId());
                intentionAgreementRoomService.updateIntentionAgreementRoom(intentionAgreement.getFormId(),spaces,intentionAgreement.getEnterpriseId());
            }
        }catch (Exception e){
            object.put("responseCode",400);
            object.put("msg",e.getMessage());
            return object;
        }
        object.put("responseCode",200);
        object.put("msg","送办成功");
        return object;
    }

    @Override
    public JSONObject getIntentionAgreementDetail(Long formId) {
        JSONObject object = new JSONObject();
        if (intentionAgreementDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该协议不存在");
            return object;
        }
        IntentionAgreement intentionAgreement =intentionAgreementDao.getIntentionAgreementByFormId(formId);
        object.put("responseCode", 200);
        object.put("form",intentionAgreement);
        return object;
    }

    @Override
    public JSONObject deleteIntentionAgreement(Long formId) {
        JSONObject object = new JSONObject();
        if (intentionAgreementDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该协议不存在");
            return object;
        }
        intentionAgreementDao.updateIntentionAgreementStatus(formId,"已删除");
        object.put("responseCode",200);
        object.put("msg","删除成功");
        return object;
    }

    @Override
    public JSONObject searchIntentionAgreementByKey(String key, String status) {
        JSONObject object = new JSONObject();
        int size = intentionAgreementDao.getSizeOfSearchIntentionAgreementByKey(key, status);
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",intentionAgreementDao.searchIntentionAgreementByKey(key, status));
        return object;
    }
}
