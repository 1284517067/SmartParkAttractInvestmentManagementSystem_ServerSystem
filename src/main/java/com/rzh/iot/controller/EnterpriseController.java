package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.Enterprise;
import com.rzh.iot.service.EnterpriseService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;

    @RequestMapping(value = "/getEnterpriseTableData")
    @JwtToken
    @ResponseBody
    public String getEnterpriseTableData(@RequestParam String currentPage, @RequestParam String limit){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("tableData",enterpriseService.getEnterpriseTableDataByLimit(Integer.parseInt(currentPage),Integer.parseInt(limit)));
        object.put("total",enterpriseService.getCountOfEnterpriseTableData());
        return object.toJSONString();
    }

    @RequestMapping(value = "/getEnterpriseDetail")
    @JwtToken
    @ResponseBody
    public String getEnterpriseDetail(@RequestParam String enterpriseId){
        JSONObject object = new JSONObject();
        HashMap<String,Object> map = enterpriseService.getEnterpriseDetail(Long.parseLong(enterpriseId));
        object.put("responseCode",map.get("responseCode"));
        object.put("msg",map.get("msg"));
        object.put("enterpriseData",map.get("enterpriseData"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/updateEnterprise")
    @JwtToken
    @ResponseBody
    public String updateEnterprise(@RequestBody String data){
        JSONObject object = new JSONObject();
        System.out.println(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        Enterprise enterprise = JSONObject.parseObject(jsonObject.getString("enterprise"),Enterprise.class);
        System.out.println(enterprise.toString());
        HashMap<String,Object> map = enterpriseService.updateEnterprise(enterprise);
        object.put("responseCode",map.get("responseCode"));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getEnterpriseListByKey")
    @JwtToken
    @ResponseBody
    public String getEnterpriseListByKey(@RequestParam String key){
        JSONObject object = new JSONObject();
        HashMap<String,Object> map = enterpriseService.getEnterpriseByKey(key);
        object.put("responseCode",map.get("responseCode"));
        object.put("tableData",map.get("tableData"));
        object.put("total",map.get("total"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getEnterpriseComponentTableData")
    @JwtToken
    @ResponseBody
    public String getEnterpriseComponentTableData(@RequestParam String currentPage, @RequestParam String limit){
        return enterpriseService.getEnterpriseComponentTableData(Integer.parseInt(currentPage),Integer.parseInt(limit)).toJSONString();
    }

    @RequestMapping(value = "/getEnterpriseComponentTableDataByKey")
    @JwtToken
    @ResponseBody
    public String getEnterpriseComponentTableDataByKey(@RequestParam String searchKey){
        return enterpriseService.getEnterpriseComponentTableDataByKey(searchKey).toJSONString();
    }

    @RequestMapping(value = "/getIntentionAgreementComponentEnterpriseData")
    @JwtToken
    @ResponseBody
    public String getIntentionAgreementComponentEnterpriseData(@RequestParam String enterpriseId){
        return enterpriseService.getIntentionAgreementComponentEnterpriseData(Long.parseLong(enterpriseId)).toJSONString();
    }
}
