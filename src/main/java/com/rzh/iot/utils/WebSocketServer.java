package com.rzh.iot.utils;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.service.MessageService;
import com.rzh.iot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

//此注解相当于设置访问url
@ServerEndpoint("/webSocket/{username}")
@JwtToken
@CrossOrigin
@Component
public class WebSocketServer {

    private Session session;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers = new CopyOnWriteArraySet<>();

    private static Map<String,Session> sessionPool = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "username") String username){
        this.session = session;
        webSocketServers.add(this);
        sessionPool.put(username,session);
        System.out.println(username + "【WebSocket】有新的连接，总数为：" + webSocketServers.size());
        try{
            MessageService messageService = SpringUtils.getBean(MessageService.class);
            UserService userService = SpringUtils.getBean(UserService.class);
            Long positionId = userService.getPositionIdByUsername(username);
            JSONObject object = messageService.generateMessageCountData(username,positionId);
            object.put("type","updateMessageCount");
            sendMessage(username,object.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @OnClose
    public void onClose(){
        webSocketServers.remove(this);
        System.out.println("【WebSocket】有连接断开,目前总数为：" + webSocketServers.size());
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("【WebSocket】收到客户端消息：" + message);
    }

    //广播消息
    public void sendAllMessage(String message){
        System.out.println("【WebSocket】广播消息：" + message);
        for (WebSocketServer webSocketServer: webSocketServers){
            try{
                webSocketServer.session.getAsyncRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //单点发送信息
    public void sendMessage(String username, String message){
        System.out.println("【WebSocket】发送信息给" + username + "，消息内容为：" + message);
        Session session = sessionPool.get(username);
        if (session != null){
            try{
                session.getAsyncRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
