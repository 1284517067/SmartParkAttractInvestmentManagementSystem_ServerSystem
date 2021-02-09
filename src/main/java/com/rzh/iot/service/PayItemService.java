package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.PayItem;
import com.rzh.iot.model.PayItemFormula;

import java.util.List;

public interface PayItemService {

    JSONObject getPayItemTableData(Integer currentPage, Integer limit);

    JSONObject updatePayItem(PayItem payItem);

    JSONObject updatePayItemFormula(Long itemId,List<PayItemFormula> payItemFormulas);

    JSONObject getPayItemDetail(Long itemId);

    List<PayItemFormula> getPayItemFormulasByItemId(Long itemId);

    JSONObject deletePayItem(Long itemId);

    JSONObject searchPayItemByKey(String key);

    JSONObject getPayItemList();


}
