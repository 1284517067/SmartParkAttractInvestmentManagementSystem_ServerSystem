package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.LeaseContract;
import com.rzh.iot.model.LeaseContractPayItem;

import java.util.List;

public interface LeaseContractService {

    JSONObject getLeaseContractTableData(Integer currentPage, Integer limit, String status);

    JSONObject getLeaseContractFormData(Long formId);

    JSONObject saveLeaseContract(LeaseContract leaseContract);

    JSONObject sendLeaseContract(LeaseContract leaseContract);

    JSONObject updateLeaseContract(LeaseContract leaseContract);

    JSONObject deleteLeaseContract(Long formId);

    JSONObject searchLeaseContractByKey(String key, String status);

    JSONObject getEnterApplicationComponentLeaseContractData(Long enterpriseId);

}
