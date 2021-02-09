package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.LeaseContractRoom;
import com.rzh.iot.model.Space;

import java.util.List;

public interface LeaseContractRoomService {

    JSONObject updateLeaseContractRooms(Long formId, List<LeaseContractRoom> leaseContractRooms);

    JSONObject updateLeaseContractRoom(Long formId, List<LeaseContractRoom> rooms, Long enterpriseId);

    List<Space> getSpacesByLeaseContract(Long formId);

    List<LeaseContractRoom> getLeaseContractRoomsByLeaseContractFormId(Long formId);

    JSONObject getLeaseContractSpaceData(Long formId);
}