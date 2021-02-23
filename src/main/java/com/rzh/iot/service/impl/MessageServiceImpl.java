package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.MessageDao;
import com.rzh.iot.model.Message;
import com.rzh.iot.service.DepartmentService;
import com.rzh.iot.service.MessageService;
import com.rzh.iot.service.PositionService;
import com.rzh.iot.service.UserService;
import com.rzh.iot.utils.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UserService userService;

    @Override
    public HashMap<String, Object> updateMessage(Message message) {
        HashMap<String,Object> map = new HashMap<>();
        if (message.getMessageId() == null){
            messageDao.createMessage(message);
        }else {
            if (messageDao.isMessageExist(message.getMessageId()) == 0){
                map.put("responseCode",400);
                map.put("msg","该消息不存在");
                return map;
            }
            messageDao.updateMessage(message);
        }
        map.put("responseCode",200);
        updateClientMessageCount(message.getPositionId());
        return map;
    }

    @Override
    public JSONObject getMessagesData(String username, Long positionId, String type) {
        JSONObject object = new JSONObject();
        Long departmentId = departmentService.getDepartmentIdByPositionId(positionId);
        List<Message> data;
        if (type == "已办"){
            data = messageDao.getMessage(type,username);
        }else {
            data = messageDao.getMessages(username,departmentId,positionId,type);
        }
        object.put("responseCode",200);
        object.put("messages",data);
        return object;
    }

    @Override
    public JSONObject getMessagesCountData(String username, Long positionId, String type) {
        JSONObject object = new JSONObject();
        Long departmentId = departmentService.getDepartmentIdByPositionId(positionId);
        if (type == "已办")
        {
            object.put("count",messageDao.getCountOfMessages(type,username));
        }else
        {
            object.put("count",messageDao.getCountOfMessage(username,departmentId,positionId,type));
        }
        object.put("responseCode",200);
        return object;
    }

    @Override
    public JSONObject generateMessageCountData(String username, Long positionId) {
        JSONObject object = new JSONObject();
        Long departmentId = departmentService.getDepartmentIdByPositionId(positionId);
        object.put("responseCode",200);
        object.put("messageCount",messageDao.getCountOfMessage(username,departmentId,positionId,"待办"));
        return object;
    }

    @Override
    public Message createMessage(Long positionId, Long formId, String msg, String applicant, String contractType) {
        Message message = new Message();
        message.setContractType(contractType);
        message.setType("待办");
        message.setStatus("正常");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        message.setCreateDate(format.format(new Date()));
        message.setFormId(formId);
        message.setPositionId(positionId);
        message.setMessage(msg);
        message.setApplicant(applicant);
        return message;
    }

    @Override
    public JSONObject updateMessageType(Long messageId, String type,String principal) {
        JSONObject object = new JSONObject();
        messageDao.updateMessageType(messageId,type,principal);
        object.put("responseCode",200);
        object.put("msg","更新成功");
        return object;
    }

    public void updateClientMessageCount(Long positionId){
        List<String> usernames = userService.getUsersByPositionId(positionId);
        if (usernames.size() == 0){
            return;
        }
        WebSocketServer webSocketServer = new WebSocketServer();
        for (String username : usernames){
            try {
                JSONObject object = generateMessageCountData(username,positionId);
                object.put("type","updateMessageCount");
                webSocketServer.sendMessage(username,object.toJSONString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
