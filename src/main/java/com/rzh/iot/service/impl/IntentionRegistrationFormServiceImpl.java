package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.IntentionRegistrationFormDao;
import com.rzh.iot.model.ApprovalOpinion;
import com.rzh.iot.model.IntentionRegistrationForm;
import com.rzh.iot.model.Message;
import com.rzh.iot.service.ApprovalOpinionService;
import com.rzh.iot.service.IntentionRegistrationFormService;
import com.rzh.iot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class
IntentionRegistrationFormServiceImpl implements IntentionRegistrationFormService {

    @Autowired
    IntentionRegistrationFormDao intentionRegistrationFormDao;

    @Autowired
    ApprovalOpinionService approvalOpinionService;

    @Autowired
    MessageService messageService;

    @Override
    public JSONObject getIntentionRegistrationFormTableData(Integer currentPage, Integer limit, String status) {
        JSONObject object = new JSONObject();
        int size = intentionRegistrationFormDao.getSizeOfIntentionRegistrationFormTableData(status);
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",intentionRegistrationFormDao.getIntentionRegistrationFormTableData((currentPage - 1) * limit, limit, status));
        return object;
    }

    @Override
    public JSONObject updateIntentionRegistrationForm(IntentionRegistrationForm form) {
        JSONObject object = new JSONObject();
        if (form.getFormId() == null){
            /**
             * 新增
             * */
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formName = format.format(new Date()) + "-" + form.getEnterpriseName() + "-" + "意向登记";
            form.setFormName(formName);
            form.setStatus("待发");
            intentionRegistrationFormDao.createIntentionRegistrationForm(form);
            object.put("responseCode",200);
            object.put("msg","保存成功");
        }else {
            /**
             * 更新
             * */
            if (intentionRegistrationFormDao.isFormExist(form.getFormId()) == 0){
                object.put("responseCode",400);
                object.put("msg","该登记不存在");
                return object;
            }
            intentionRegistrationFormDao.updateIntentionRegistrationForm(form);
            object.put("responseCode",200);
            object.put("msg","保存成功");
        }
        return object;
    }

    @Override
    public JSONObject getIntentionRegistrationFormDetail(Long formId) {
        JSONObject object = new JSONObject();
        if (intentionRegistrationFormDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该意向登记不存在");
            return object;
        }
        object.put("responseCode",200);
        object.put("form",intentionRegistrationFormDao.getIntentionRegistrationFormByFormId(formId));
        return object;
    }

    @Override
    public JSONObject sendIntentionRegistrationForm(IntentionRegistrationForm form) {
        /**
         * 更新/新增意向登记
         * */
        JSONObject object = updateIntentionRegistrationForm(form);
        if ((int)object.get("responseCode") == 400){
            return object;
        }
        /**
         * 校验
         * */
        if (intentionRegistrationFormDao.isFormExist(form.getFormId()) == 0){
            object.put("responseCode",400);
            object.put("msg","该意向登记不存在");
            return object;
        }
        /**
         * 更新状态
         * */
        intentionRegistrationFormDao.updateStatusByFormId(form.getFormId(),"已发");
        /**
         * 根据启用审批流创建节点
         * */
        HashMap<String,Object> map =  approvalOpinionService.createApprovalOpinions(form.getFormId(),"意向登记");

        /**
         * 若无启用审批流，则默认审批完成，创建企业档案
         * */
        if ((int)map.get("responseCode") == 404){
            intentionRegistrationFormDao.updateApprovalStatus(form.getFormId(),"审批完成");
            /**
             * 创建企业档案
             * */
            HashMap<String,Object> map1 = approvalOpinionService.initEnterprise(form.getFormId());
            object.put("responseCode",map1.get("responseCode"));
            object.put("msg",map1.get("msg"));
            return object;
        }
        /**
         * 获取审批流里首个待审批按钮
         * */
        ApprovalOpinion approvalOpinion = approvalOpinionService.getFirstWaitingApprovalOpinion(form.getFormId(),"意向登记");
        intentionRegistrationFormDao.updateApprovalStatus(form.getFormId(),"等待" + approvalOpinion.getApprovalProcessNodeName());
        Message message = new Message();
        message.setFormId(form.getFormId());
        message.setMessage(form.getFormName() + "-" + approvalOpinion.getApprovalProcessNodeName());
        message.setApplicant(form.getPrincipal());
        message.setPositionId(approvalOpinion.getPositionId());
        message.setType("待办");
        message.setStatus("正常");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        message.setCreateDate(format.format(new Date()));
        message.setContractType("意向登记");

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

    @Override
    public JSONObject getIntentionRegistrationFormTableByKey(String key, String status) {
        JSONObject object = new JSONObject();
        int size = intentionRegistrationFormDao.getSizeOfKey(key,status);
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",intentionRegistrationFormDao.getIntentionRegistrationFormsByKey(key, status));
        return object;
    }

    @Override
    public JSONObject deleteIntentionRegistrationForm(Long formId) {
        JSONObject object = new JSONObject();
        if (intentionRegistrationFormDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该意向登记不存在");
            return object;
        }
        intentionRegistrationFormDao.updateStatusByFormId(formId,"已删除");
        object.put("responseCode",200);
        object.put("msg","删除成功");
        return object;
    }

}
