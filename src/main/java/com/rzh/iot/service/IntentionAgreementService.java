package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.IntentionAgreement;
import com.rzh.iot.model.Space;

import java.util.List;

public interface IntentionAgreementService {

    JSONObject getIntentionAgreementTableData(Integer currentPage, Integer limit, String status);

    JSONObject saveIntentionAgreementForm(IntentionAgreement intentionAgreement);

    JSONObject sendIntentionAgreementFrom(IntentionAgreement intentionAgreement);

    JSONObject getIntentionAgreementDetail(Long formId);

    JSONObject deleteIntentionAgreement(Long formId);

    JSONObject searchIntentionAgreementByKey(String key, String status);

}
