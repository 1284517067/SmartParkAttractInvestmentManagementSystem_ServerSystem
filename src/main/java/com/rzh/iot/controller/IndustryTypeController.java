package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.IndustryType;
import com.rzh.iot.service.IndustryTypeService;
import com.rzh.iot.utils.JwtToken;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class IndustryTypeController {

    @Autowired
    IndustryTypeService industryTypeService;

    @RequestMapping(value = "/getIndustryTypes")
    @ResponseBody
    @JwtToken
    public String getIndustryTypes(@RequestParam String currentPage , @RequestParam String limit){
        List<IndustryType> industryTypeData = industryTypeService.getIndustryTypeByLimit(Integer.parseInt(currentPage), Integer.parseInt(limit));
        System.out.println(industryTypeData.toString());
        JSONObject object = new JSONObject();
        object.put("industryTypeData",industryTypeData);
        object.put("responseCode",200);
        return object.toJSONString();
    }

    @RequestMapping(value = "/getIndustryTypeTree")
    @JwtToken
    @ResponseBody
    public String getIndustryTypeTree(){
        List<IndustryType> node = industryTypeService.getIndustryTypeTree();
        JSONObject object = new JSONObject();
        object.put("node",node);
        object.put("responseCode",200);
        return object.toJSONString();
    }

    @RequestMapping(value = "/getParentTypeData")
    @JwtToken
    @ResponseBody
    public String getParentTypeData(){
        List<IndustryType> list = industryTypeService.getIndustryTypes();
        JSONObject object = new JSONObject();
        object.put("parentNodes",list);
        object.put("responseCode",200);
        return object.toJSONString();
    }

    @RequestMapping(value = "/postNewIndustryType")
    @JwtToken
    @ResponseBody
    public String postNewIndustryType(@RequestParam String industryTypeName,@RequestParam String industryTypeId ,@RequestParam String remark ,@RequestParam String fatherNodeId){
        IndustryType industryType = new IndustryType();
        if (industryTypeId != null && industryTypeId != ""){
            industryType.setIndustryTypeId(Long.parseLong(industryTypeId));
        }
        if (fatherNodeId != null && fatherNodeId != ""){
            industryType.setFatherNodeId(Long.parseLong(fatherNodeId));
        }
        industryType.setIndustryTypeName(industryTypeName);
        industryType.setRemark(remark);
        System.out.println(industryType.toString());
        JSONObject object = new JSONObject();
        HashMap<String,String> map = industryTypeService.updateIndustryType(industryType);
        switch (map.get("responseCode")){
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

    @RequestMapping(value = "/deleteIndustryTypeById")
    @JwtToken
    @ResponseBody
    public String deleteIndustryTypeById(@RequestParam String industryTypeId){
        JSONObject object = new JSONObject();
        if (industryTypeService.deleteIndustryTypeById(Long.parseLong(industryTypeId))){
            object.put("responseCode",200);
            object.put("msg","删除成功");
        }else {
            object.put("responseCode",400);
            object.put("msg","删除失败");
        }
        return object.toJSONString();
    }

    @RequestMapping(value = "/getIndustryTypeDataByKey")
    @JwtToken
    @ResponseBody
    public String getIndustryTypeDataByKey(@RequestParam String key){
        List<IndustryType> list =industryTypeService.getIndustryTypeDataByKey(key);
        JSONObject object = new JSONObject();
        if (list.size() == 0){
            object.put("responseCode",400);
            object.put("msg","暂无数据");
            return object.toJSONString();
        }
        object.put("industryTypeData",list);
        object.put("responseCode",200);
        return object.toJSONString();
    }

    @RequestMapping(value = "/getIndustryTypeDataTotalCount")
    @JwtToken
    @ResponseBody
    public String getIndustryTypeDataTotalCount(){
        Integer count = industryTypeService.getIndustryTypeDataTotalCount();
        JSONObject object = new JSONObject();
        object.put("total",count);
        object.put("responseCode",200);
        return object.toJSONString();
    }

    @RequestMapping(value = "/getIndustryTypeSelectData")
    @JwtToken
    @ResponseBody
    public String getIndustryTypeSelectData(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("industryTypeList",industryTypeService.getIndustryTypeSelectData());
        return object.toJSONString();
    }
}
