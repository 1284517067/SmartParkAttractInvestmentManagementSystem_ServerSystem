package com.rzh.iot.service.impl;

import com.rzh.iot.dao.IndustryTypeDao;
import com.rzh.iot.model.IndustryType;
import com.rzh.iot.service.IndustryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service

public class IndustryTypeServiceImpl implements IndustryTypeService {

    @Autowired
    IndustryTypeDao industryTypeDao;

    @Override
    public List<IndustryType> getIndustryTypes() {
        List<IndustryType> list = industryTypeDao.getIndustryType();
        return list;
    }

    @Override
    public List<IndustryType> getIndustryTypeTree() {
        List<IndustryType> list = industryTypeDao.getIndustryType();
        List<IndustryType> tree = new ArrayList<>();
        for (IndustryType item  :list){
            if (item.getFatherNodeId() == null){
                tree.add(item);
            }
        }
        for (IndustryType industryType : tree){
            List<IndustryType> children = new ArrayList<>();
            for (IndustryType item : list){
                if (item.getFatherNodeId() == industryType.getIndustryTypeId()){
                    children.add(item);
                }
            }
            industryType.setNodes(children);
        }
        return tree;
    }

    @Override
    public List<IndustryType> getIndustryTypeByLimit(Integer currentPage, Integer limit) {
        List<IndustryType> list = industryTypeDao.getIndustryTypeByLimit((currentPage -1) * limit,limit);
        for (IndustryType item : list){
            if (item.getFatherNodeId() != null){
                item.setParentTypeName(industryTypeDao.getParentNameById(item.getFatherNodeId()));
            }
        }
        return list;
    }

    @Override
    public HashMap<String,String> updateIndustryType(IndustryType industryType) {
        int result ;
        HashMap<String,String> map = new HashMap<>();
        if(industryType.getIndustryTypeId() != null){
            result = industryTypeDao.updateIndustryType(industryType);
        }else {
            if (!isNameInvoke(industryType)){
                map.put("responseCode","400");
                map.put("msg","行业名称已存在");
                return map;
            }
            industryType.setStatus("正常");
            result = industryTypeDao.createIndustryType(industryType);
        }
        if (result == 1){
            map.put("responseCode","200");
            map.put("msg","保存成功");
        }else {
            map.put("responseCode","400");
            map.put("msg","保存失败");
        }
        return map;
    }

    @Override
    public List<IndustryType> getIndustryTypeDataByKey(String key) {
        List<IndustryType> list = industryTypeDao.getIndustryTypeDataByKey(key);
        System.out.println(list.toString());
        for (IndustryType item : list){
            if (item.getFatherNodeId() != null){
                item.setParentTypeName(industryTypeDao.getParentNameById(item.getFatherNodeId()));
            }
        }
        return list;
    }

    @Override
    public Integer getIndustryTypeDataTotalCount() {
        Integer count = industryTypeDao.getTotalCount();
        return count;
    }

    @Override
    public List<IndustryType> getIndustryTypeSelectData() {
        return industryTypeDao.getIndustryTypeList();
    }

    @Override
    public boolean deleteIndustryTypeById(Long industryTypeId) {
        boolean flag = false;
        if (industryTypeDao.deleteIndustryTypeById(industryTypeId) == 1){
            flag = true;
        }
        return flag;
    }

    public boolean isNameInvoke(IndustryType industryType){
        if (industryTypeDao.isNameInvoke(industryType) == 0 ){
            return true;
        }
        return false;
    }
}
