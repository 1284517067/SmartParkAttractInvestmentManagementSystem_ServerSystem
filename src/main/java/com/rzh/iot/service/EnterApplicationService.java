package com.rzh.iot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.EnterApplication;

public interface EnterApplicationService {

    JSONObject getEnterApplicationTableData(Integer limit, Integer currentPage, String status);

    JSONObject getEnterApplicationDetailData(Long formId);

    JSONObject saveEnterApplication(EnterApplication enterApplication);

    JSONObject sendEnterApplication(EnterApplication enterApplication);
}
