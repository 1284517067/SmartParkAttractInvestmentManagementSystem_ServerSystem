package com.rzh.iot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.Enterprise;

import java.util.HashMap;
import java.util.List;

public interface EnterpriseService {

    List<Enterprise> getEnterpriseTableDataByLimit(Integer currentPage,Integer limit);

    int getCountOfEnterpriseTableData();

    HashMap<String,Object> getEnterpriseDetail(Long enterpriseId);

    HashMap<String,Object> updateEnterprise(Enterprise enterprise);

    HashMap<String,Object> getEnterpriseByKey(String key);

    JSONObject getEnterpriseComponentTableData(Integer currentPage,Integer limit);

    JSONObject getEnterpriseComponentTableDataByKey(String key);

    JSONObject getIntentionAgreementComponentEnterpriseData(Long enterpriseId);
}
