package com.rzh.iot.controller;

import com.rzh.iot.service.ApprovalOpinionService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class ApprovalOpinionController {

    @Autowired
    ApprovalOpinionService approvalOpinionService;

    @RequestMapping(value = "/getApprovalOpinionStepByFormId")
    @JwtToken
    @ResponseBody
    public String getApprovalOpinionStepByFormId(@RequestParam String formId,@RequestParam String contractType){
        return approvalOpinionService.getApprovalOpinionStepByFormId(Long.parseLong(formId),contractType).toJSONString();
    }

    @RequestMapping(value = "/updateOpinion")
    @JwtToken
    @ResponseBody
    public String updateOpinion(@RequestParam String opinionId, @RequestParam String opinion, @RequestParam String status, @RequestParam String formId, @RequestParam String contractType, @RequestParam String messageId, @RequestParam String principal){
        return approvalOpinionService.updateOpinion(Long.parseLong(opinionId),opinion,status,Long.parseLong(formId),contractType,Long.parseLong(messageId),principal).toJSONString();
    }
}
