package com.rzh.iot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.IntentionAgreement;
import com.rzh.iot.model.Space;
import com.rzh.iot.service.IntentionAgreementService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/")
@JwtToken
@RestController
public class IntentionAgreementController {

    @Autowired
    IntentionAgreementService intentionAgreementService;

    @RequestMapping(value = "/getIntentionAgreementTableData")
    @JwtToken
    @ResponseBody
    public String getIntentionAgreementTableData(@RequestParam String currentPage, @RequestParam String limit, @RequestParam String status){
        return intentionAgreementService.getIntentionAgreementTableData(Integer.parseInt(currentPage),Integer.parseInt(limit),status).toJSONString();
    }

    @RequestMapping(value = "/saveIntentionAgreementForm")
    @JwtToken
    @ResponseBody
    public String saveIntentionAgreementForm(@RequestBody String data){
        JSONObject object = JSONObject.parseObject(data);
        IntentionAgreement intentionAgreement = object.getObject("intentionAgreementForm",IntentionAgreement.class);
        JSONArray jsonArray = object.getJSONArray("spaces");
        List<Space> spaces = jsonArray.toJavaList(Space.class);
        intentionAgreement.setSpaces(spaces);
        return intentionAgreementService.saveIntentionAgreementForm(intentionAgreement).toJSONString();
    }

    @RequestMapping(value = "/sendIntentionAgreementFrom")
    @JwtToken
    @ResponseBody
    public String sendIntentionAgreementFrom(@RequestBody String data){
        JSONObject object = JSONObject.parseObject(data);
        IntentionAgreement intentionAgreement = object.getObject("intentionAgreementForm",IntentionAgreement.class);
        JSONArray jsonArray = object.getJSONArray("spaces");
        List<Space> spaces = jsonArray.toJavaList(Space.class);
        intentionAgreement.setSpaces(spaces);
        return intentionAgreementService.sendIntentionAgreementFrom(intentionAgreement).toJSONString();
    }

    @RequestMapping(value = "/getIntentionAgreementForm")
    @JwtToken
    @ResponseBody
    public String getIntentionAgreementForm(@RequestParam String formId){
        return intentionAgreementService.getIntentionAgreementDetail(Long.parseLong(formId)).toJSONString();
    }

    @RequestMapping(value = "/deleteIntentionAgreement")
    @JwtToken
    @ResponseBody
    public String deleteIntentionAgreement(@RequestParam String formId){
        return intentionAgreementService.deleteIntentionAgreement(Long.parseLong(formId)).toJSONString();
    }
    
    @RequestMapping(value = "/searchIntentionAgreementByKey")
    @JwtToken
    @ResponseBody
    public String searchIntentionAgreementByKey(@RequestParam String searchKey, @RequestParam String status){
        return intentionAgreementService.searchIntentionAgreementByKey(searchKey, status).toJSONString();
    }
}
