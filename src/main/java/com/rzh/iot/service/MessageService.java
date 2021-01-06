package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.ApprovalOpinion;
import com.rzh.iot.model.Message;

import java.util.HashMap;

public interface MessageService {

    HashMap<String, Object> updateMessage(Message message);

    JSONObject getMessagesData(String username, Long positionId, String type);

    JSONObject getMessagesCountData(String username, Long positionId, String type);

    JSONObject generateMessageCountData(String username, Long positionId);

    Message createMessage(Long positionId, Long formId, String formName, String applicant, String contractType);

    JSONObject updateMessageType(Long messageId, String type, String principal);
}
