package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.*;
import com.rzh.iot.service.LeaseContractService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class LeaseContractController {

    @Autowired
    LeaseContractService leaseContractService;

    @RequestMapping(value = "/getLeaseContractTableData")
    @JwtToken
    @ResponseBody
    public String getLeaseContractTableData(@RequestParam String currentPage,@RequestParam String limit, @RequestParam String status){
        return leaseContractService.getLeaseContractTableData(Integer.parseInt(currentPage),Integer.parseInt(limit),status).toJSONString();
    }

    @RequestMapping(value = "/getLeaseContractFormData")
    @JwtToken
    @ResponseBody
    public String getLeaseContractFormData(@RequestParam String formId){
        return leaseContractService.getLeaseContractFormData(Long.parseLong(formId)).toJSONString();
    }

    @RequestMapping(value = "/saveLeaseContract")
    @JwtToken
    @ResponseBody
    public String saveLeaseContract(@RequestBody String data){
        JSONObject object = JSONObject.parseObject(data);
        LeaseContract leaseContract = object.getObject("leaseContract",LeaseContract.class);
        leaseContract.setSpaces(object.getJSONArray("spaces").toJavaList(LeaseContractRoom.class));
        leaseContract.setPayItems(object.getJSONArray("payItems").toJavaList(LeaseContractPayItem.class));
        return leaseContractService.saveLeaseContract(leaseContract).toJSONString();
    }

    @RequestMapping(value = "/sendLeaseContract")
    @JwtToken
    @ResponseBody
    public String sendLeaseContract(@RequestBody String data){
        JSONObject object = JSONObject.parseObject(data);
        LeaseContract leaseContract = object.getObject("leaseContract",LeaseContract.class);
        leaseContract.setSpaces(object.getJSONArray("spaces").toJavaList(LeaseContractRoom.class));
        leaseContract.setPayItems(object.getJSONArray("payItems").toJavaList(LeaseContractPayItem.class));
        return leaseContractService.sendLeaseContract(leaseContract).toJSONString();
    }

    @RequestMapping(value = "/deleteLeaseContract")
    @JwtToken
    @ResponseBody
    public String deleteLeaseContract(@RequestParam String formId){
        return leaseContractService.deleteLeaseContract(Long.parseLong(formId)).toJSONString();
    }

    @RequestMapping(value = "/searchLeaseContractByKey")
    @JwtToken
    @ResponseBody
    public String searchLeaseContractByKey(@RequestParam String searchKey, @RequestParam String status){
        return leaseContractService.searchLeaseContractByKey(searchKey, status).toJSONString();
    }
}
