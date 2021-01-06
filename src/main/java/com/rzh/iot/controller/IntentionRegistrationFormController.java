package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.IntentionRegistrationForm;
import com.rzh.iot.service.IntentionRegistrationFormService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class IntentionRegistrationFormController {

    @Autowired
    IntentionRegistrationFormService intentionRegistrationFormService;

    @RequestMapping(value = "/getIntentionRegistrationFormTableData")
    @JwtToken
    @ResponseBody
    public String getIntentionRegistrationFormTableData(@RequestParam String currentPage, @RequestParam String limit, @RequestParam String status){
        JSONObject object = intentionRegistrationFormService.getIntentionRegistrationFormTableData(Integer.parseInt(currentPage), Integer.parseInt(limit), status);
        return object.toJSONString();
    }

    @RequestMapping(value = "/updateIntentionRegistrationForm")
    @JwtToken
    @ResponseBody
    public String updateIntentionRegistrationForm(@RequestBody String data){
        JSONObject object = JSONObject.parseObject(data);
        IntentionRegistrationForm form = object.getObject("intentionRegistrationForm",IntentionRegistrationForm.class);
        return intentionRegistrationFormService.updateIntentionRegistrationForm(form).toJSONString();
    }

    @RequestMapping(value = "/getIntentionRegistrationFormDetail")
    @JwtToken
    @ResponseBody
    public String getIntentionRegistrationFormDetail(@RequestParam String formId){
        return intentionRegistrationFormService.getIntentionRegistrationFormDetail(Long.parseLong(formId)).toJSONString();
    }

    @RequestMapping(value = "/sendIntentionRegistrationForm")
    @JwtToken
    @ResponseBody
    public String sendIntentionRegistrationForm(@RequestBody String data){
        JSONObject object = JSONObject.parseObject(data);
        IntentionRegistrationForm form = object.getObject("form",IntentionRegistrationForm.class);
        return intentionRegistrationFormService.sendIntentionRegistrationForm(form).toJSONString();
    }

    @RequestMapping(value = "/getIntentionRegistrationFormTableByKey")
    @JwtToken
    @ResponseBody
    public String getIntentionRegistrationFormTableByKey(@RequestParam String searchKey, @RequestParam String status){
        return intentionRegistrationFormService.getIntentionRegistrationFormTableByKey(searchKey, status).toJSONString();
    }

    @RequestMapping(value = "/deleteIntentionRegistrationForm")
    @JwtToken
    @ResponseBody
    public String deleteIntentionRegistrationForm(@RequestParam String formId){
        return intentionRegistrationFormService.deleteIntentionRegistrationForm(Long.parseLong(formId)).toJSONString();
    }
}
