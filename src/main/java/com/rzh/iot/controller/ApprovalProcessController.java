package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.ApprovalProcess;
import com.rzh.iot.service.ApprovalProcessService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class ApprovalProcessController {

    @Autowired
    ApprovalProcessService approvalProcessService;

    @RequestMapping(value = "/getApprovalProcessByLimit")
    @JwtToken
    @ResponseBody
    public String getApprovalProcessByLimit(@RequestParam String currentPage,@RequestParam String limit){
       List<ApprovalProcess> result = approvalProcessService.getApprovalProcessByLimit(Integer.parseInt(currentPage),Integer.parseInt(limit));
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("approvalProcessData",result);
        return object.toJSONString();
    }

    @RequestMapping(value = "/getApprovalProcessTotal")
    @JwtToken
    @ResponseBody
    public String getApprovalProcessTotal(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("total",approvalProcessService.getApprovalProcessTotal());
        return object.toJSONString();
    }

    @RequestMapping(value = "/postApprovalProcess")
    @JwtToken
    @ResponseBody
    public String postApprovalProcess(@RequestParam String approvalProcessId, @RequestParam String approvalProcessName ,@RequestParam String contractType,@RequestParam String businessType,@RequestParam String processDescription){
        ApprovalProcess approvalProcess = new ApprovalProcess();
        if (approvalProcessId != ""){
            approvalProcess.setApprovalProcessId(Long.parseLong(approvalProcessId));
        }
        approvalProcess.setApprovalProcessName(approvalProcessName);
        approvalProcess.setContractType(contractType);
        approvalProcess.setBusinessType(businessType);
        approvalProcess.setProcessDescription(processDescription);


        JSONObject object = new JSONObject();

        HashMap<String,String> map =  approvalProcessService.updateApprovalProcess(approvalProcess);
        switch (map.get("code")){
            case "200":
                object.put("responseCode",200);
                object.put("msg",map.get("msg"));
                break;
            case "400":
                object.put("responseCode",400);
                object.put("msg",map.get("msg"));
                break;
        }

        return object.toJSONString();
    }

    @RequestMapping(value = "/searchApprovalProcessByKey")
    @JwtToken
    @ResponseBody
    public String searchApprovalProcessByKey(@RequestParam String key){
        JSONObject object = new JSONObject();
        List<ApprovalProcess> list = approvalProcessService.getApprovalProcessByKey(key);
        if (list.size() == 0){
            object.put("responseCode",400);
            object.put("msg","暂无数据");
            return object.toJSONString();
        }

        object.put("responseCode",200);
        object.put("approvalProcessData",list);
        object.put("total",approvalProcessService.getCountOfApprovalProcessByKey(key));
        return object.toJSONString();
    }

    @RequestMapping(value = "/switchApprovalProcessStatus")
    @JwtToken
    @ResponseBody
    public String switchApprovalProcessStatus(@RequestParam String approvalProcessId , @RequestParam String status,@RequestParam String contractType , @RequestParam String businessType){
        JSONObject object = new JSONObject();
        if (status.equals("启用")){
            if (!approvalProcessService.isInvoke(contractType,businessType)){
                object.put("responseCode",400);
                object.put("msg","同一合同类型及业务类型审批流在同一时刻只能有一个启用");
                return object.toJSONString();
            }
        }

        if (approvalProcessService.changeApprovalProcessStatus(Long.parseLong(approvalProcessId),status)){
            object.put("responseCode",200);
            object.put("msg",status+"成功");
        }else {
            object.put("responseCode",400);
            object.put("msg",status+"失败");
        }

        return object.toJSONString();
    }

    @RequestMapping(value = "/getApprovalProcessArrayData")
    @JwtToken
    @ResponseBody
    public String getApprovalProcessArrayData(){
        List<ApprovalProcess> list = approvalProcessService.getApprovalProcessIdAndApprovalProcessName();
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("approvalProcessArrayData",list);
        return object.toJSONString();
    }

    @RequestMapping(value = "/deleteApprovalProcessData")
    @JwtToken
    @ResponseBody
    public String deleteApprovalProcessData(@RequestParam String approvalProcessId){
        JSONObject object = new JSONObject();
        HashMap<String,String> map = approvalProcessService.deleteApprovalProcessByApprovalProcessId(Long.parseLong(approvalProcessId));
        object.put("responseCode",Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }



}
