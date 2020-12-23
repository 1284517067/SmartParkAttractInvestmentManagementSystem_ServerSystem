package com.rzh.iot.service;

import com.rzh.iot.model.Enterprise;

import java.util.HashMap;
import java.util.List;

public interface EnterpriseService {

    List<Enterprise> getEnterpriseTableDataByLimit(Integer currentPage,Integer limit);

    int getCountOfEnterpriseTableData();

    HashMap<String,Object> getEnterpriseDetail(Long enterpriseId);

    HashMap<String,Object> updateEnterprise(Enterprise enterprise);

    HashMap<String,Object> getEnterpriseByKey(String key);
}
