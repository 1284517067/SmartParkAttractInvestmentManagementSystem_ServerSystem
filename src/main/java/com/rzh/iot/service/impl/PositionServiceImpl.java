package com.rzh.iot.service.impl;

import com.rzh.iot.dao.PositionDao;
import com.rzh.iot.model.Position;
import com.rzh.iot.service.DepartmentService;
import com.rzh.iot.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionDao positionDao;

    @Autowired
    DepartmentService departmentService;

    @Override
    public String getPositionNameById(Long positionId) {
        return positionDao.getPositionNameById(positionId);
    }

    @Override
    public List<Position> getPositionByDepartmentId(Long departmentId) {
        return getPositionByDepartmentId(departmentId);
    }

    @Override
    public List<Position> getPositionList(Integer currentPage,Integer limit) {
        List<Position> list = positionDao.getPositionList((currentPage -1)*limit , limit);
        for (Position item : list){
            item.setDepartmentName(departmentService.getDepartmentNameByPositionId(item.getPositionId()));
        }
        return list;
    }

    @Override
    public Integer getPositionTotal() {
        return positionDao.getPositionTotal();
    }

    @Override
    public HashMap<String, String> updatePosition(String positionId, String positionName, String departmentId, String submitType) {
        HashMap<String,String> map = new HashMap<>();
        Position position = new Position();
        if (positionId != ""){
            position.setPositionId(Long.parseLong(positionId));
        }
        position.setPositionName(positionName);
        position.setDepartmentId(Long.parseLong(departmentId));
        switch (submitType){
            case "新增":
                if (positionDao.isNameDuplication(position) != 0){
                    map.put("responseCode","400");
                    map.put("msg","该部门已存在该职位");
                    return map;
                }
                positionDao.createPosition(position);
                map.put("responseCode","200");
                map.put("msg","保存成功");
                break;
            case "更新":
                if (positionDao.isExist(position) == 0){
                    map.put("responseCode","400");
                    map.put("msg","该职位不存在");
                    return map;
                }
                positionDao.updatePosition(position);
                map.put("responseCode","200");
                map.put("msg","更新成功");
                break;
        }
        return map;
    }

    @Override
    public int deletePositionByPositionId(Long positionId) {
        return positionDao.deletePositionByPositionId(positionId);
    }

    @Override
    public List<Position> getPositionListByKey(String key) {
        return positionDao.getPositionListByKey(key);
    }

    @Override
    public int getPositionCountByKey(String key) {
        return positionDao.getPositionCountByKey(key);
    }
}
