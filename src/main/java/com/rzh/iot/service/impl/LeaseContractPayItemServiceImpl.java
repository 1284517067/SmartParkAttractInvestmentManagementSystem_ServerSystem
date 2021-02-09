package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.LeaseContractDao;
import com.rzh.iot.dao.LeaseContractPayItemDao;
import com.rzh.iot.model.LeaseContractPayItem;
import com.rzh.iot.service.LeaseContractPayItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseContractPayItemServiceImpl implements LeaseContractPayItemService {

    @Autowired
    LeaseContractDao leaseContractDao;

    @Autowired
    LeaseContractPayItemDao leaseContractPayItemDao;

    @Override
    public JSONObject updateLeaseContractPayItem(Long formId, List<LeaseContractPayItem> payItems) {
        JSONObject object = new JSONObject();
        if (leaseContractDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该租赁合同不存在");
            return object;
        }
        leaseContractPayItemDao.deleteLeaseContractPayItemsByFormId(formId);

        if (payItems.size() > 0){
            payItems.forEach(leaseContractPayItem -> {
                leaseContractPayItem.setFormId(formId);
            });
            leaseContractPayItemDao.createLeaseContractPayItems(payItems);
        }
        object.put("responseCode", 200);
        object.put("msg", "更新成功");
        return object;
    }

    @Override
    public JSONObject getLeaseContractPayItemData(Long formId) {
        JSONObject object = new JSONObject();
        if (leaseContractDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该记录不存在");
            return object;
        }
        List<LeaseContractPayItem> payItems = leaseContractPayItemDao.getLeaseContractPayItemData(formId);
        object.put("responseCode",200);
        object.put("payItems",payItems);
        return object;
    }
}
