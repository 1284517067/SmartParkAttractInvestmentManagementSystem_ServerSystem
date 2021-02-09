package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.LeaseContractPayItem;

import java.util.List;

public interface LeaseContractPayItemService {

    JSONObject updateLeaseContractPayItem(Long formId, List<LeaseContractPayItem> payItems);

    JSONObject getLeaseContractPayItemData(Long formId);

}
