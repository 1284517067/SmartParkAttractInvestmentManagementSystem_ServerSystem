package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;

public interface ApprovalService {
    JSONObject getFormData(Long formId,String contractType);
}
