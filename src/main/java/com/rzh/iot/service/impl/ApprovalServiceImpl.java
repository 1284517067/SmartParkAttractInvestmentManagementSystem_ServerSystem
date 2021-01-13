package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.service.ApprovalService;
import com.rzh.iot.service.IntentionAgreementService;
import com.rzh.iot.service.IntentionRegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    IntentionRegistrationFormService intentionRegistrationFormService;

    @Autowired
    IntentionAgreementService intentionAgreementService;

    @Override
    public JSONObject getFormData(Long formId, String contractType) {
        JSONObject object = new JSONObject() ;
        switch (contractType){
            case "意向登记":
                object =  intentionRegistrationFormService.getIntentionRegistrationFormDetail(formId);
                break;
            case "意向协议":
                object = intentionAgreementService.getIntentionAgreementDetail(formId);
                break;
            default:
                object.put("responseCode",400);
                object.put("msg","暂无该类型合同");
        }
        return object;
    }
}
