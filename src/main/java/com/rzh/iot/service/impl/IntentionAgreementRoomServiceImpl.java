package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.IntentionAgreementDao;
import com.rzh.iot.dao.IntentionAgreementRoomDao;
import com.rzh.iot.model.IntentionAgreementRoom;
import com.rzh.iot.model.Space;
import com.rzh.iot.service.IntentionAgreementRoomService;
import com.rzh.iot.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntentionAgreementRoomServiceImpl implements IntentionAgreementRoomService {

    @Autowired
    IntentionAgreementRoomDao intentionAgreementRoomDao;

    @Autowired
    IntentionAgreementDao intentionAgreementDao;

    @Autowired
    SpaceService spaceService;


    /**
     * 更新意向协议中的房间信息并更新房间状态
     * */
    @Override
    public JSONObject updateIntentionAgreementRoom(Long formId, List<Space> spaces, Long enterpriseId) {
        JSONObject object = new JSONObject();
        if (intentionAgreementDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该协议不存在");
            return object;
        }

        /**
         * 还原历史记录里的房间状态
         * */
        List<Space> oldSpaces = getSpacesByIntentionAgreement(formId);
        resetSpaces(oldSpaces);
        /**
         * 更新新的房间状态
         * */
        List<IntentionAgreementRoom> rooms = new ArrayList<>();
        for (Space space : spaces){
            if (!spaceService.updateSpaceStatusBySpaceId(space.getSpaceId(),"意向中") || !spaceService.updateSpaceEnterpriseIdBySpaceId(space.getSpaceId(),enterpriseId)){
                object.put("responseCode",400);
                object.put("msg","房间状态更新时发生错误");
                return object;
            }
            IntentionAgreementRoom intentionAgreementRoom = new IntentionAgreementRoom();
            intentionAgreementRoom.setFormId(formId);
            intentionAgreementRoom.setSpaceId(space.getSpaceId());
            rooms.add(intentionAgreementRoom);
        }
        intentionAgreementRoomDao.deleteIntentionAgreementRooms(formId);
        if (rooms.size() > 0){
            intentionAgreementRoomDao.createIntentionAgreementRooms(rooms);
        }
        object.put("responseCode",200);
        object.put("msg","更新成功");
        return object;
    }


    /**
     * 只更新意向协议中的房间信息
     * */
    @Override
    public JSONObject updateIntentionAgreementRooms(Long formId, List<Space> spaces) {
        JSONObject object = new JSONObject();
        if (intentionAgreementDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该协议不存在");
            return object;
        }

        intentionAgreementRoomDao.deleteIntentionAgreementRooms(formId);
        if (spaces.size() > 0){
            List<IntentionAgreementRoom> rooms = new ArrayList<>();
            for (Space space : spaces){
                IntentionAgreementRoom room = new IntentionAgreementRoom();
                room.setFormId(formId);
                room.setSpaceId(space.getSpaceId());
                rooms.add(room);
            }
            intentionAgreementRoomDao.createIntentionAgreementRooms(rooms);
        }
        object.put("responseCode",200);
        object.put("msg","更新成功");
        return object;
    }

    @Override
    public List<Space> getSpacesByIntentionAgreement(Long formId) {
        List<Space> spaces = new ArrayList<>();
        List<IntentionAgreementRoom> rooms = intentionAgreementRoomDao.getIntentionAgreementRoomsByFormId(formId);
        for (IntentionAgreementRoom room : rooms){
            Space space = spaceService.getSpaceBySpaceId(room.getSpaceId());
            spaces.add(space);
        }
        return spaces;
    }

    @Override
    public JSONObject getIntentionAgreementComponentSpaceData(Long formId) {
        JSONObject object = new JSONObject();
        List<Space> spaces = getSpacesByIntentionAgreement(formId);
        for(Space space : spaces){
            Space parentSpace = spaceService.getSpaceNameAndParentNodeId(space.getParentNodeId());
            Space grandParentSpace = spaceService.getSpaceNameAndParentNodeId(parentSpace.getParentNodeId());
            Space greatGrandParentSpace = spaceService.getSpaceNameAndParentNodeId(grandParentSpace.getParentNodeId());
            space.setFloorName(parentSpace.getSpaceName());
            space.setBuildingName(grandParentSpace.getSpaceName());
            space.setParkName(greatGrandParentSpace.getSpaceName());
        }
        object.put("responseCode",200);
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
