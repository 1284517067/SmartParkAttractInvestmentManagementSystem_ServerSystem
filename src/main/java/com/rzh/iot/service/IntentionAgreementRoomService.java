package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.IntentionAgreement;
import com.rzh.iot.model.Space;

import java.util.List;

public interface IntentionAgreementRoomService {

    JSONObject updateIntentionAgreementRoom(Long formId, List<Space> spaces, Long enterpriseId);

    JSONObject updateIntentionAgreementRooms(Long formId, List<Space> spaces);

    List<Space> getSpacesByIntentionAgreement(Long formId);

    JSONObject getIntentionAgreementComponentSpaceData(Long formId);

}
