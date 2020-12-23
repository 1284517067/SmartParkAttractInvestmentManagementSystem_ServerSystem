package com.rzh.iot.service.impl;

import com.rzh.iot.dao.DepartmentDao;
import com.rzh.iot.dao.PositionDao;
import com.rzh.iot.model.Department;
import com.rzh.iot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    PositionDao positionDao;

    @Override
    public String getDepartmentNameById(Long departmentId) {
        return departmentDao.getDepartmentNameById(departmentId);
    }

    @Override
    public String getDepartmentNameByPositionId(Long positionId) {
        return departmentDao.getDepartmentNameByPositionId(positionId);
    }

    @Override
    public List<Department> getDepartmentListData() {
        List<Department> list = departmentDao.getDepartmentListData();
        for (Department item : list){
            item.setPositions(positionDao.getPositionByDepartmentId(item.getDepartmentId()));
        }
        return list;
    }

    @Override
    public List<Department> getDepartmentTreeData() {
        List<Department> list = departmentDao.getDepartmentListData();
        for (Department item : list){
            item.setPositions(positionDao.getPositionByDepartmentId(item.getDepartmentId()));
        }
        return list;
    }

    @Override
    public List<Department> getDepartmentListByLimit(Integer currentPage, Integer limit) {
        return departmentDao.getDepartmentListByLimit((currentPage - 1) * limit, limit);
    }

    @Override
    public HashMap<String, String> updateDepartment(String departmentId, String departmentName, String submitType) {
        HashMap<String,String> map = new HashMap<>();
        Department department = new Department();
        if (departmentId != ""){
            department.setDepartmentId(Long.parseLong(departmentId));
        }
        department.setDepartmentName(departmentName);
        switch (submitType){
            case "新增":
                if (departmentDao.isNameDuplication(department) != 0){
                    map.put("responseCode","400");
                    map.put("msg","该部门已存在");
                    return map;
                }
                departmentDao.createDepartment(department);
                map.put("responseCode","200");
                map.put("msg","保存成功");
                break;
            case "更新":
                if (departmentDao.isExist(department) == 0){
                    map.put("responseCode","400");
                    map.put("msg","该部门不存在");
                    return map;
                }
                if (departmentDao.isNameDuplication(department) > 1){
                    map.put("responseCode","400");
                    map.put("msg","该部门已存在");
                    return map;
                }
                departmentDao.updateDepartment(department);
                map.put("responseCode","200");
                map.put("msg","更新成功");
                break;
        }
        return map;
    }

    @Override
    public Integer getDepartmentTotal() {

        return departmentDao.getDepartmentTotal();
    }

    @Override
    public int deleteDepartmentByDepartmentId(Long departmentId) {
        return departmentDao.deleteDepartmentByDepartmentId(departmentId);
    }

    @Override
    public List<Department> getDepartmentListByKey(String key) {
        return departmentDao.getDepartmentListByKey(key);
    }

    @Override
    public int getDepartmentCountByKey(String key) {
        return departmentDao.getDepartmentCountByKey(key);
    }

    @Override
    public Long getDepartmentIdByPositionId(Long positionId) {
        return departmentDao.getDepartmentIdByPositionId(positionId);
    }
}
