package com.rzh.iot.controller;

import com.rzh.iot.service.MessageService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/getMessagesData")
    @JwtToken
    @ResponseBody
    public String getMessagesData(@RequestParam String username, @RequestParam String positionId, @RequestParam String type){
        return messageService.getMessagesData(username,Long.parseLong(positionId),type).toJSONString();
    }

    @RequestMapping(value = "/getMessagesCountData")
    @JwtToken
    @ResponseBody
    public String getMessagesCountData(@RequestParam String username, @RequestParam String positionId, @RequestParam String type){
        return messageService.getMessagesCountData(username,Long.parseLong(positionId),type).toJSONString();
    }

    @RequestMapping(value = "/generateMessageCountData")
    @JwtToken
    @ResponseBody
    public String generateMessageCountData(@RequestParam String username, @RequestParam String positionId){
        return messageService.generateMessageCountData(username,Long.parseLong(positionId)).toJSONString();
    }

}
