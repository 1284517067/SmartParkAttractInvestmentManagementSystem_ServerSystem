package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.ApprovalOpinionDao;
import com.rzh.iot.dao.ApprovalProcessDao;
import com.rzh.iot.dao.IntentionAgreementDao;
import com.rzh.iot.dao.IntentionRegistrationFormDao;
import com.rzh.iot.model.*;
import com.rzh.iot.service.*;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ApprovalOpinionServiceImpl implements ApprovalOpinionService {

    @Autowired
    ApprovalProcessDao approvalProcessDao;

    @Autowired
    ApprovalProcessNodeService approvalProcessNodeService;

    @Autowired
    ApprovalOpinionDao approvalOpinionDao;

    @Autowired
    MessageService messageService;

    @Autowired
    IntentionRegistrationFormService intentionRegistrationFormService;

    @Autowired
    IntentionRegistrationFormDao intentionRegistrationFormDao;

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    IntentionAgreementService intentionAgreementService;

    @Autowired
    IntentionAgreementRoomService intentionAgreementRoomService;

    @Autowired
    IntentionAgreementDao intentionAgreementDao;

    @Override
    public HashMap<String, Object> createApprovalOpinions(Long formId, String contractType,String businessType) {
        HashMap<String,Object> map = new HashMap<>();
        JSONObject object = approvalProcessNodeService.getApprovalProcessNodesByContractType(contractType,businessType);
        if (object == null){
            map.put("responseCode",404);
            map.put("msg","该合同类型没有启用的审批流");
            return map;
        }
        List<ApprovalProcessNode> nodes = (List<ApprovalProcessNode>) object.get("approvalProcess");
        List<ApprovalOpinion> opinions = new ArrayList<>();
        for (ApprovalProcessNode node:nodes){
            ApprovalOpinion approvalOpinion = new ApprovalOpinion();
            approvalOpinion.setApprovalProcessNodeName(node.getApprovalProcessNodeName());
            approvalOpinion.setFormId(formId);
            approvalOpinion.setPositionId(node.getPositionId());
            approvalOpinion.setStatus("待审批");
            approvalOpinion.setContractType(contractType);
            opinions.add(approvalOpinion);
        }
        for(int i = 0 ; i < opinions.size(); i++){
            if (i != 0){
                opinions.get(i).setPrevNode(opinions.get(i-1).getFormId());
            }
            approvalOpinionDao.createApprovalOpinion(opinions.get(i));
        }
        map.put("responseCode",200);
        map.put("msg","送办成功");
        return map;
    }

    @Override
    public JSONObject getApprovalOpinionStepByFormId(Long formId,String contractType) {
        JSONObject object = new JSONObject();
        List<ApprovalOpinion> list = approvalOpinionDao.getStepByFormId(formId,contractType);

        object.put("responseCode",200);
        object.put("step",list);
        return object;
    }

    @Override
    public ApprovalOpinion getFirstWaitingApprovalOpinion(Long formId, String contractType) {

        return approvalOpinionDao.getFirstWaitingApprovalOpinion(formId,contractType);

    }

    /**
     * 领导审批意见
     * */
    @Override
    public JSONObject updateOpinion(Long opinionId, String opinion, String status, Long formId, String contractType, Long messageId, String principal) {
        JSONObject object = new JSONObject();
        /**
         * 校验该节点是否存在
         * */
        if (approvalOpinionDao.isOpinionExist(opinionId) == 0){
            object.put("responseCode",400);
            object.put("msg","该审批节点不存在");
            return object;
        }
        /**
         * 更新该节点
         * */
        approvalOpinionDao.updateOpinion(opinionId,opinion,status);
        /**
         * 更新消息状态
         * */
        messageService.updateMessageType(messageId,"已办",principal);

        /**
         * 审批状态为拒绝：更新合同状态，结束审批流
         * */
        if (status.equals("拒绝"))
        {
            switch (contractType){
                case "意向登记":
                    intentionRegistrationFormDao.updateApprovalStatus(formId,status);
                    break;
            }
            object.put("responseCode",200);
            object.put("msg","审批成功");
            return object;
        }

        /**
         * 若审批结果为“同意”，创建下一审批部门的审批消息
         * */

        String formName = "";
        String principal1 = "";

        /**
         * 获取合同名称及申请人
         * */
        switch (contractType){
            case "意向登记":
                IntentionRegistrationForm intentionRegistrationForm = intentionRegistrationFormService.getIntentionRegistrationFormDetail(formId).getObject("form", IntentionRegistrationForm.class);
                formName = intentionRegistrationForm.getFormName();
                principal1 = intentionRegistrationForm.getPrincipal();
                break;
            case "意向协议":
                IntentionAgreement intentionAgreement = intentionAgreementService.getIntentionAgreementDetail(formId).getObject("form",IntentionAgreement.class);
                formName = intentionAgreement.getFormName();
                principal1 = intentionAgreement.getApplicant();
        }


        /**
         * 获取审批流中第一个待审批的节点
         * */
        ApprovalOpinion approvalOpinion = getFirstWaitingApprovalOpinion(formId,contractType);
        if (approvalOpinion != null){
            /**
             * 更新合同状态
             * */
            intentionRegistrationFormDao.updateApprovalStatus(formId,"等待" + approvalOpinion.getApprovalProcessNodeName());
            /**
             * 创建消息对象
             * */
            Message message = messageService.createMessage(approvalOpinion.getPositionId(),formId,formName + "-" + approvalOpinion.getApprovalProcessNodeName(),principal1,contractType);
            /**
             * 数据库插入消息
             * */
            HashMap<String,Object> map = messageService.updateMessage(message);

            if ((int)map.get("responseCode") == 400){
                object.put("responseCode",map.get("responseCode"));
                object.put("msg",map.get("msg"));
                return object;
            }
        }else {
            /**
             * 审批流程到达最后一个节点，结束该审批流
             * */
            /**
             * 审批完成后各个合同的操作
             * */
            switch (contractType){
                case "意向登记":

                    intentionRegistrationFormDao.updateApprovalStatus(formId,"审批完成");

                    HashMap<String,Object> map = initEnterprise(formId);
                    if ((int)map.get("responseCode") == 400){
                        object.put("responseCode",map.get("responseCode"));
                        object.put("msg",map.get("msg"));
                        return object;
                    }
                    break;
                case "意向协议":

                    intentionAgreementDao.updateIntentionAgreementApprovalStatus(formId,"审批完成");

                    IntentionAgreement intentionAgreement = intentionAgreementService.getIntentionAgreementDetail(formId).getObject("form",IntentionAgreement.class);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        Date deadline = format.parse(intentionAgreement.getDeadline());
                        if (deadline.getTime() >= format.parse(format.format(new Date())).getTime()){
                            List<Space> spaces = intentionAgreementRoomService.getSpacesByIntentionAgreement(intentionAgreement.getFormId());
                            intentionAgreementRoomService.updateIntentionAgreementRoom(intentionAgreement.getFormId(),spaces,intentionAgreement.getDeadline());
                        }
                    }catch (Exception e){
                        object.put("responseCode",400);
                        object.put("msg",e.getMessage());
                        return object;
                    }
                    break;

            }
        }
        object.put("responseCode",200);
        object.put("msg","审批成功");
        return object;
    }

    public HashMap<String,Object> initEnterprise(Long formId){
        IntentionRegistrationForm form = intentionRegistrationFormDao.getIntentionRegistrationFormByFormId(formId);
        Enterprise enterprise = new Enterprise();
        BeanUtils.copyProperties(form,enterprise);
        enterprise.setStatus("正常");
        HashMap<String,Object> map = enterpriseService.updateEnterprise(enterprise);
        return map;
    }

}
