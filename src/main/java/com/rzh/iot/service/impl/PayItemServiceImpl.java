package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.PayItemDao;
import com.rzh.iot.dao.PayItemFormulaDao;
import com.rzh.iot.model.LeaseContractPayItem;
import com.rzh.iot.model.LeaseContractRoom;
import com.rzh.iot.model.PayItem;
import com.rzh.iot.model.PayItemFormula;
import com.rzh.iot.service.PayItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PayItemServiceImpl implements PayItemService {

    @Autowired
    PayItemDao payItemDao;

    @Autowired
    PayItemFormulaDao payItemFormulaDao;


    @Override
    public JSONObject getPayItemTableData(Integer currentPage, Integer limit) {
        JSONObject object = new JSONObject();
        int size = payItemDao.getSizeOfPayItemTable();
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        List<PayItem> payItems = payItemDao.getPayItemTableData((currentPage - 1) * limit , limit);
        object.put("tableData",payItems);
        return object;
    }

    @Override
    public JSONObject updatePayItem(PayItem payItem) {
        JSONObject object = new JSONObject();
        if (payItem.getItemId() == null){
            /**
             * 新增
             * */
            if (payItemDao.isNameExist(payItem.getItemName()) != 0){
                object.put("responseCode", 400);
                object.put("msg","已存在该名称的收费项");
                return object;
            }
            payItemDao.createPayItem(payItem);
        }else {
            /**
             * 更新
             * */
            if (payItemDao.isPayItemExist(payItem.getItemId()) == 0){
                object.put("responseCode",400);
                object.put("msg","该收费项不存在");
            }
            payItemDao.updatePayItem(payItem);
        }
        object = updatePayItemFormula(payItem.getItemId(),payItem.getFormulas());
        return object;
    }

    @Override
    public JSONObject updatePayItemFormula(Long itemId, List<PayItemFormula> payItemFormulas) {
        JSONObject object = new JSONObject();
        if (payItemFormulaDao.isRoomsExist(itemId) != 0){
            payItemFormulaDao.deleteFormulasByItemId(itemId);
        }
        if (payItemFormulas.size() > 0){
            payItemFormulas.forEach(payItemFormula -> {
                payItemFormula.setItemId(itemId);
            });
            payItemFormulaDao.createPayItemFormulas(payItemFormulas);
        }
        object.put("responseCode",200);
        object.put("msg","保存成功");
        return object;
    }

    @Override
    public JSONObject getPayItemDetail(Long itemId) {
        JSONObject object = new JSONObject();
        if (payItemDao.isPayItemExist(itemId) == 0){
            object.put("responseCode", 400);
            object.put("msg","该收费项不存在");
            return object;
        }
        PayItem payItem = payItemDao.getPayItemDetail(itemId);
        payItem.setFormulas(getPayItemFormulasByItemId(payItem.getItemId()));
        object.put("responseCode",200);
        object.put("payItem",payItem);
        return object;
    }

    @Override
    public List<PayItemFormula> getPayItemFormulasByItemId(Long itemId) {
        return payItemFormulaDao.getPayItemFormulasByItemId(itemId);
    }

    @Override
    public JSONObject deletePayItem(Long itemId) {
        JSONObject object = new JSONObject();
        if (payItemDao.isPayItemExist(itemId) == 0){
            object.put("responseCode", 400);
            object.put("msg","该收费项不存在");
            return object;
        }
        payItemDao.deletePayItem(itemId);
        if (payItemFormulaDao.isRoomsExist(itemId) != 0){
            payItemFormulaDao.deleteFormulasByItemId(itemId);
        }
        object.put("responseCode",200);
        object.put("msg","删除成功");
        return object;
    }

    @Override
    public JSONObject searchPayItemByKey(String key) {
        JSONObject object = new JSONObject();
        int size = payItemDao.getSizeOfPayItemByKey(key);
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",payItemDao.getPayItemByKey(key));
        return object;
    }

    @Override
    public JSONObject getPayItemList() {
        JSONObject object = new JSONObject();
        object.put("responseCode", 200);
        object.put("payItemList", payItemDao.getPayItemList());
        return object;
    }

}
