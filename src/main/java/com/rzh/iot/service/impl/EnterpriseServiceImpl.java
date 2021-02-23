package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.EnterpriseDao;
import com.rzh.iot.model.Enterprise;
import com.rzh.iot.model.EnterpriseEnterPark;
import com.rzh.iot.service.EnterpriseEnterParkService;
import com.rzh.iot.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    EnterpriseDao enterpriseDao;

    @Autowired
    EnterpriseEnterParkService enterpriseEnterParkService;


    @Override
    public List<Enterprise> getEnterpriseTableDataByLimit(Integer currentPage,Integer limit) {
        List<Enterprise> enterprises = enterpriseDao.getEnterpriseList((currentPage - 1)* limit, limit);
        for (Enterprise enterprise : enterprises){
            enterprise.setEnterParks(enterpriseDao.getEnterpriseEnterParksByEnterpriseId(enterprise.getEnterpriseId()));
            String spaceName = "",recordDate = "";
            for (int i = 0 ; i < enterprise.getEnterParks().size(); i++){
                if (i != 0){
                    spaceName += ",";
                    recordDate += ",";
                }
                spaceName += enterprise.getEnterParks().get(i).getSpaceName();
                recordDate += enterprise.getEnterParks().get(i).getRecordDate();
            }
            enterprise.setSpaceName(spaceName);
            enterprise.setEnterTime(recordDate);
        }
        return enterprises;
    }

    @Override
    public int getCountOfEnterpriseTableData() {
        return enterpriseDao.getCountOfEnterpriseList();
    }

    @Override
    public HashMap<String, Object> getEnterpriseDetail(Long enterpriseId) {
        HashMap<String,Object> map = new HashMap<>();
        if (enterpriseDao.isEnterpriseExist(enterpriseId) == 0){
            map.put("responseCode",400);
            map.put("msg","该企业不存在");
            return map;
        }
        map.put("responseCode",200);
        map.put("enterpriseData",enterpriseDao.getEnterpriseByEnterpriseId(enterpriseId));
        return map;
    }

    @Override
    public HashMap<String, Object> updateEnterprise(Enterprise enterprise) {
        HashMap<String,Object> map = new HashMap<>();
        if (enterprise.getEnterpriseId() == null){
            /**
             * 新增
             * */
            if(enterpriseDao.isEnterpriseNameExist(enterprise.getEnterpriseName()) != 0){
                map.put("responseCode",400);
                map.put("msg","该企业名称已存在");
                return map;
            }
            enterpriseDao.createEnterprise(enterprise);
            map.put("responseCode",200);
            map.put("msg","保存成功");

        }else {
            /**
             * 更新
             * */
            if (enterpriseDao.isEnterpriseExist(enterprise.getEnterpriseId()) == 0){
                map.put("responseCode",400);
                map.put("msg","该企业不存在");
                return map;
            }
            if(enterpriseDao.isEnterpriseNameExist(enterprise.getEnterpriseName()) > 1){
                map.put("responseCode",400);
                map.put("msg","该企业名称已存在");
                return map;
            }
            enterpriseDao.updateEnterprise(enterprise);
            map.put("responseCode",200);
            map.put("msg","保存成功");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> getEnterpriseByKey(String key) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("responseCode",200);
        map.put("tableData",enterpriseDao.getEnterpriseListByKey(key));
        map.put("total",enterpriseDao.getSizeOfEnterpriseListByKey(key));
        return map;
    }

    @Override
    public JSONObject getEnterpriseComponentTableData(Integer currentPage, Integer limit) {
        JSONObject object = new JSONObject();
        int size = enterpriseDao.getSizeOfEnterpriseComponentTableData();
        object.put("responseCode", 200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",enterpriseDao.getEnterpriseComponentTableData((currentPage -1) * limit,limit));
        return object;
    }

    @Override
    public JSONObject getEnterpriseComponentTableDataByKey(String key) {
        JSONObject object = new JSONObject();
        int size = enterpriseDao.getSizeOfEnterpriseComponentTableDataByKey(key);
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",enterpriseDao.getEnterpriseComponentTableDataByKey(key));
        return object;
    }

    @Override
    public JSONObject getIntentionAgreementComponentEnterpriseData(Long enterpriseId) {
        JSONObject object = new JSONObject();
        if (enterpriseDao.isEnterpriseExist(enterpriseId) == 0){
            object.put("responseCode",400);
            object.put("msg","该企业不存在");
            return object;
        }
        object.put("responseCode",200);
        object.put("enterprise",enterpriseDao.getIntentionAgreementComponentEnterpriseData(enterpriseId));
        return object;
    }
}
