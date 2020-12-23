package com.rzh.iot.service;

import com.rzh.iot.model.Department;

import java.util.HashMap;
import java.util.List;

public interface DepartmentService {
    String getDepartmentNameById(Long departmentId);

    String getDepartmentNameByPositionId(Long positionId);

    List<Department> getDepartmentListData();

    List<Department> getDepartmentTreeData();

    List<Department> getDepartmentListByLimit(Integer currentPage,Integer limit);

    HashMap<String,String> updateDepartment(String departmentId,String departmentName ,String submitType);

    Integer getDepartmentTotal();

    int deleteDepartmentByDepartmentId(Long departmentId);

    List<Department> getDepartmentListByKey(String key);

    int getDepartmentCountByKey(String key);

    Long getDepartmentIdByPositionId(Long positionId);
}
