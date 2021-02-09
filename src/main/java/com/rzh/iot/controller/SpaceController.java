package com.rzh.iot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.Space;
import com.rzh.iot.service.IntentionAgreementRoomService;
import com.rzh.iot.service.LeaseContractRoomService;
import com.rzh.iot.service.SpaceService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class SpaceController {

    @Autowired
    SpaceService spaceService;

    @Autowired
    IntentionAgreementRoomService intentionAgreementRoomService;

    @Autowired
    LeaseContractRoomService leaseContractRoomService;

    @RequestMapping(value = "/getSpaceTree")
    @JwtToken
    @ResponseBody
    public String getSpaceTree(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("spaceTree",spaceService.getSpaceTreeData());
        return object.toJSONString();
    }

    @RequestMapping(value = "/lazyLoadSpaceLeaf")
    @JwtToken
    @ResponseBody
    public String lazyLoadSpaceLeaf(@RequestParam String spaceId){
        JSONObject object =  new JSONObject();
        object.put("responseCode",200);
        object.put("leafData",spaceService.getSpaceTreeLeaf(Long.parseLong(spaceId)));
        return object.toJSONString();
    }

    @RequestMapping(value = "/submitSpaceForm")
    @JwtToken
    @ResponseBody
    public String updateSpace(@RequestBody String space){
        JSONObject object = new JSONObject();
        JSONObject jsonSpace = JSON.parseObject(space);
        Space space1 = jsonSpace.getObject("space",Space.class);
        HashMap<String,String> map = spaceService.updateSpace(space1);
        object.put("responseCode",Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/deleteSpace")
    @JwtToken
    @ResponseBody
    public String deleteSpace(@RequestParam String spaceId){
        JSONObject object = new JSONObject();
        HashMap<String,String> map = spaceService.deleteSpace(Long.parseLong(spaceId));
        object.put("responseCode",Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getParkSelect")
    @JwtToken
    @ResponseBody
    public String getParkSelect(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("parkList",spaceService.getParkList());
        return object.toJSONString();
    }

    @RequestMapping(value = "/lazyLoadIntentionAgreementSpaceLeaf")
    public String lazyLoadIntentionAgreementSpaceLeaf(@RequestParam String spaceId, @RequestParam String enterpriseId){
        return spaceService.lazyLoadIntentionAgreementSpaceLeaf(Long.parseLong(spaceId), Long.parseLong(enterpriseId)).toJSONString();
    }

    @RequestMapping(value = "/getIntentionAgreementComponentSpaceData")
    @JwtToken
    @ResponseBody
    public String getIntentionAgreementComponentSpaceData(@RequestParam String formId){
        return intentionAgreementRoomService.getIntentionAgreementComponentSpaceData(Long.parseLong(formId)).toJSONString();
    }

    @RequestMapping(value = "/getLeaseContractSpaceData")
    @JwtToken
    @ResponseBody
    public String getLeaseContractSpaceData(@RequestParam String formId){
        return leaseContractRoomService.getLeaseContractSpaceData(Long.parseLong(formId)).toJSONString();
    }
}
