package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.IntentionRegistrationForm;

public interface IntentionRegistrationFormService {

    JSONObject getIntentionRegistrationFormTableData(Integer currentPage,Integer limit,String status);

    JSONObject updateIntentionRegistrationForm(IntentionRegistrationForm form);

    JSONObject getIntentionRegistrationFormDetail(Long formId);

    JSONObject sendIntentionRegistrationForm(IntentionRegistrationForm form);

    JSONObject getIntentionRegistrationFormTableByKey(String key,String status);

    JSONObject deleteIntentionRegistrationForm(Long formId);

}
