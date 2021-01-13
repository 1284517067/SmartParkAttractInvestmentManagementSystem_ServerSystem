package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.ApprovalProcess;
import com.rzh.iot.service.ApprovalProcessNodeService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class ApprovalProcessNodeController {

    @Autowired
    ApprovalProcessNodeService approvalProcessNodeService;

    @RequestMapping(value = "/getApprovalProcessNodeData")
    @JwtToken
    @ResponseBody
    public String getApprovalProcessNodeData(@RequestParam String approvalProcessId){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("approvalProcessNode",approvalProcessNodeService.getApprovalProcessNodeData(Long.parseLong(approvalProcessId)));
        return object.toJSONString();
    }

    @RequestMapping(value = "/postApprovalProcessNodeForm")
    @JwtToken
    @ResponseBody
    public String postApprovalProcessNodeForm(@RequestParam String approvalProcessId,@RequestParam String nodes,@RequestParam String submitType){
        HashMap<String,String> map = approvalProcessNodeService.updateApprovalProcessNode(approvalProcessId,nodes,submitType);
        JSONObject object = new JSONObject();
        object.put("responseCode",Long.parseLong(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getApprovalProcessNodeListData")
    @JwtToken
    @ResponseBody
    public String getApprovalProcessNodeListData(@RequestParam String currentPage , @RequestParam String limit){
        JSONObject object = new JSONObject();
        List<ApprovalProcess> list = approvalProcessNodeService.getApprovalProcessNodeListData(Integer.parseInt(currentPage),Integer.parseInt(limit));
        object.put("responseCode",200);
        object.put("dataList",list);
        return object.toJSONString();
    }

    @RequestMapping(value = "/getApprovalProcessNodeCount")
    @JwtToken
    @ResponseBody
    public String getApprovalProcessNodeCount(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("count",approvalProcessNodeService.getApprovalProcessCount());
        return object.toJSONString();
    }

    @RequestMapping(value = "/deleteApprovalProcessNodeByApprovalProcessId")
    @JwtToken
    @ResponseBody
    public String deleteApprovalProcessNodeByApprovalProcessId(@RequestParam String approvalProcessId){
        JSONObject object = new JSONObject();
        HashMap<String,String> map = approvalProcessNodeService.deleteApprovalProcessNodeByApprovalProcessId(Long.parseLong(approvalProcessId));
        object.put("responseCode",Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getApprovalProcessNodeListByKey")
    @JwtToken
    @ResponseBody
    public String getApprovalProcessNodeListByKey(@RequestParam String searchKey){
        JSONObject object = new JSONObject();
        HashMap<String,Object> map = approvalProcessNodeService.getApprovalProcessNodeListByKey(searchKey);
        object.put("responseCode",(Integer) map.get("responseCode"));
        switch ((Integer)map.get("responseCode")){
            case 200:
               object.put("dataList",(List<ApprovalProcess>)map.get("dataList"));
               object.put("total",approvalProcessNodeService.getCountOfApprovalProcessNodeByKey(searchKey));
               object.put("msg",(String)map.get("msg"));
               break;
            case 400:
                object.put("msg",(String)map.get("msg"));
                break;
        }
        return object.toJSONString();
    }

    @RequestMapping(value = "/getApprovalProcessNodesByContractType")
    @JwtToken
    @ResponseBody
    public String getApprovalProcessNodesByContractType(@RequestParam String contractType, @RequestParam String businessType){
        return approvalProcessNodeService.getApprovalProcessNodesByContractType(contractType,businessType).toJSONString();
    }

}
