package com.rzh.iot.service;

import com.rzh.iot.model.Position;

import java.util.HashMap;
import java.util.List;

public interface PositionService {
    String getPositionNameById(Long positionId);

    List<Position> getPositionByDepartmentId(Long departmentId);

    List<Position> getPositionList(Integer currentPage,Integer limit);

    Integer getPositionTotal();

    HashMap<String,String> updatePosition(String positionId, String positionName, String departmentId, String submitType);

    int deletePositionByPositionId(Long positionId);

    List<Position> getPositionListByKey(String key);

    int getPositionCountByKey(String key);
}
