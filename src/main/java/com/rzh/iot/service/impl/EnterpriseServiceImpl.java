package com.rzh.iot.service.impl;

import com.rzh.iot.dao.EnterpriseDao;
import com.rzh.iot.model.Enterprise;
import com.rzh.iot.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    EnterpriseDao enterpriseDao;

    @Override
    public List<Enterprise> getEnterpriseTableDataByLimit(Integer currentPage,Integer limit) {
        return enterpriseDao.getEnterpriseList((currentPage - 1)* limit, limit);
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
}
