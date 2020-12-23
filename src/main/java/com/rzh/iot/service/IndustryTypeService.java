package com.rzh.iot.service;

import com.rzh.iot.model.IndustryType;

import java.util.HashMap;
import java.util.List;

public interface IndustryTypeService {

    List<IndustryType> getIndustryTypes();

    List<IndustryType> getIndustryTypeTree();

    List<IndustryType> getIndustryTypeByLimit(Integer currentPage , Integer limit);

    HashMap<String,String> updateIndustryType(IndustryType industryType);

    boolean deleteIndustryTypeById(Long industryTypeId);

    List<IndustryType> getIndustryTypeDataByKey(String key);

    Integer getIndustryTypeDataTotalCount();

    List<IndustryType> getIndustryTypeSelectData();
}
