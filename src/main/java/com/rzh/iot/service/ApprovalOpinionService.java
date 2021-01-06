package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.ApprovalOpinion;

import java.util.HashMap;

public interface ApprovalOpinionService {
    HashMap<String,Object> createApprovalOpinions(Long formId,String contractType);

    JSONObject getApprovalOpinionStepByFormId(Long formId,String contractType);

    ApprovalOpinion getFirstWaitingApprovalOpinion(Long formId,String contractType);

    JSONObject updateOpinion(Long opinionId,String opinion, String status,Long formId, String contractType,Long messageId,String principal);

    HashMap<String,Object> initEnterprise(Long formId);
}
