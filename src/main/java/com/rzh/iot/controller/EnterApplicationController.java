package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.EnterApplication;
import com.rzh.iot.service.EnterApplicationService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class EnterApplicationController {

    @Autowired
    EnterApplicationService enterApplicationService;

    @RequestMapping(value = "/getEnterApplicationTableData")
    @JwtToken
    @ResponseBody
    public String getEnterApplicationTableData(@RequestParam String limit, @RequestParam String currentPage, @RequestParam String status){
        return enterApplicationService.getEnterApplicationTableData(Integer.parseInt(limit),Integer.parseInt(currentPage),status).toJSONString();
    }

    @RequestMapping(value = "/getEnterApplicationDetailData")
    @JwtToken
    @ResponseBody
    public String getEnterApplicationDetailData(@RequestParam String formId){
        return enterApplicationService.getEnterApplicationDetailData(Long.parseLong(formId)).toJSONString();
    }

    @RequestMapping(value = "/saveEnterApplication")
    @JwtToken
    @ResponseBody
    public String saveEnterApplication(@RequestBody String data){
        JSONObject object = JSONObject.parseObject(data);
        EnterApplication enterApplication = object.getObject("enterApplication",EnterApplication.class);
        return enterApplicationService.saveEnterApplication(enterApplication).toJSONString();
    }

    @RequestMapping(value = "/sendEnterApplication")
    @JwtToken
    @ResponseBody
    public String sendEnterApplication(@RequestBody String data){
        JSONObject object = JSONObject.parseObject(data);
        EnterApplication enterApplication = object.getObject("enterApplication",EnterApplication.class);
        return enterApplicationService.sendEnterApplication(enterApplication).toJSONString();
    }
}
