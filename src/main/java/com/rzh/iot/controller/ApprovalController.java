package com.rzh.iot.controller;

import com.rzh.iot.service.ApprovalService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class ApprovalController {

    @Autowired
    ApprovalService approvalService;

    @RequestMapping(value = "/getFormData")
    @JwtToken
    @ResponseBody
    public String getFormData(@RequestParam String formId, @RequestParam String contractType){
        return approvalService.getFormData(Long.parseLong(formId),contractType).toJSONString();
    }
}
