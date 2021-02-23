package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.LeaseContractDao;
import com.rzh.iot.dao.LeaseContractRoomDao;
import com.rzh.iot.model.LeaseContractRoom;
import com.rzh.iot.model.Space;
import com.rzh.iot.service.LeaseContractRoomService;
import com.rzh.iot.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaseContractRoomServiceImpl implements LeaseContractRoomService {

    @Autowired
    LeaseContractRoomDao leaseContractRoomDao;

    @Autowired
    LeaseContractDao leaseContractDao;

    @Autowired
    SpaceService spaceService;

    /**
     * 只更新租赁申请中的房间信息
     * */
    @Override
    public JSONObject updateLeaseContractRooms(Long formId,List<LeaseContractRoom> leaseContractRooms) {
        JSONObject object = new JSONObject();
        if (leaseContractDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该租赁合同不存在");
            return object;
        }
        leaseContractRoomDao.deleteLeaseContractRoomsByFormId(formId);
        if (leaseContractRooms.size() > 0){
            leaseContractRooms.forEach(leaseContractRoom -> {
                leaseContractRoom.setFormId(formId);
            });
            leaseContractRoomDao.createLeaseContractRooms(leaseContractRooms);
        }
        object.put("responseCode", 200);
        object.put("msg", "更新成功");
        return object;
    }

    /**
     * 更新租赁合同中的房间信息并更改房间状态
     * */
    @Override
    public JSONObject updateLeaseContractRoom(Long formId, List<LeaseContractRoom> rooms, Long enterpriseId) {
        JSONObject object = new JSONObject();
        if (leaseContractDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该租赁合同不存在");
            return object;
        }

        /**
         * 还原旧的房间状态
         * */
        List<Space> oldSpace = getSpacesByLeaseContract(formId);
        resetSpaces(oldSpace);
        leaseContractRoomDao.deleteLeaseContractRoomsByFormId(formId);
        /**
         * 更新新的房间状态
         * */
        if (rooms.size() > 0){
            leaseContractRoomDao.createLeaseContractRooms(rooms);
            for (LeaseContractRoom room : rooms){
                if (!spaceService.updateSpaceStatusBySpaceId(room.getSpaceId(),"已租") || !spaceService.updateSpaceEnterpriseIdBySpaceId(room.getSpaceId(),enterpriseId)){
                    object.put("responseCode",400);
                    object.put("msg","房间状态更新时发生错误");
                    return object;
                }
            }
        }
        object.put("responseCode",200);
        object.put("msg","更新成功");
        return object;
    }

    @Override
    public List<Space> getSpacesByLeaseContract(Long formId) {
        List<Space> spaces = new ArrayList<>();
        List<LeaseContractRoom> rooms = leaseContractRoomDao.getLeaseContractRoomByFormId(formId);
        for (LeaseContractRoom room : rooms){
            Space space = spaceService.getSpaceBySpaceId(room.getSpaceId());
            spaces.add(space);
        }
        return spaces;
    }

    @Override
    public List<LeaseContractRoom> getLeaseContractRoomsByLeaseContractFormId(Long formId) {
        return leaseContractRoomDao.getLeaseContractRoomByFormId(formId);
    }

    @Override
    public JSONObject getLeaseContractSpaceData(Long formId) {
        JSONObject object = new JSONObject();
        List<Space> spaces = getSpacesByLeaseContract(formId);
        for (Space space : spaces){
            Space parentSpace = spaceService.getSpaceNameAndParentNodeId(space.getParentNodeId());
            Space grandParentSpace = spaceService.getSpaceNameAndParentNodeId(parentSpace.getParentNodeId());
            Space greatGrandParentSpace = spaceService.getSpaceNameAndParentNodeId(grandParentSpace.getParentNodeId());
            space.setFloorId(parentSpace.getSpaceId());
            space.setFloorName(parentSpace.getSpaceName());
            space.setBuildingId(grandParentSpace.getSpaceId());
            space.setBuildingName(grandParentSpace.getSpaceName());
            space.setParkId(greatGrandParentSpace.getSpaceId());
            space.setParkName(greatGrandParentSpace.getSpaceName());
        }
        object.put("responseCode", 200);
        object.put("spaces",spaces);
        return object;
    }

    private void resetSpaces(List<Space> spaces){
        for (Space space :spaces){
            spaceService.updateSpaceStatusBySpaceId(space.getSpaceId(),"待租");
            spaceService.updateSpaceEnterpriseIdBySpaceId(space.getSpaceId(),null);
        }
    }
}
