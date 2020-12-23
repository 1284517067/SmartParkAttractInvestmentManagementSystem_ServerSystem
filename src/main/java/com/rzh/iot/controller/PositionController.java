package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.service.PositionService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class PositionController {

    @Autowired
    PositionService positionService;

    @RequestMapping(value = "/getPositionByDepartmentId")
    @JwtToken
    @ResponseBody
    public String getPositionByDepartmentId(@RequestParam String departmentId){
        JSONObject object = new JSONObject();
        object.put("responseCode" , 200);
        object.put("positionList",positionService.getPositionByDepartmentId(Long.parseLong(departmentId)));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getPositionTableData")
    @JwtToken
    @ResponseBody
    public String getPositionTableData(@RequestParam String currentPage,@RequestParam String limit){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("positionTableData",positionService.getPositionList(Integer.parseInt(currentPage),Integer.parseInt(limit)));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getPositionTotal")
    @JwtToken
    @ResponseBody
    public String getPositionTotal(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("dataTotal",positionService.getPositionTotal());
        return object.toJSONString();
    }

    @RequestMapping(value = "/createPosition")
    @JwtToken
    @ResponseBody
    public String updatePosition(@RequestParam String positionId, @RequestParam String positionName, @RequestParam String departmentId,@RequestParam String submitType){
        JSONObject object = new JSONObject();
        HashMap<String,String> map = positionService.updatePosition(positionId,positionName,departmentId,submitType);
        object.put("responseCode",Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/deletePositionByPositionId")
    @JwtToken
    @ResponseBody
    public String deletePositionByPositionId(@RequestParam String positionId){
        JSONObject object = new JSONObject();
        if (positionService.deletePositionByPositionId(Long.parseLong(positionId)) == 0){
            object.put("responseCode",400);
            object.put("msg","删除失败");
        }else {
            object.put("responseCode",200);
            object.put("msg","删除成功");
        }
        return object.toJSONString();
    }

    @RequestMapping(value = "/getPositionDataByKey")
    @JwtToken
    @ResponseBody
    public String getPositionDataByKey(@RequestParam String searchKey){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("tableData",positionService.getPositionListByKey(searchKey));
        object.put("dataTotal",positionService.getPositionCountByKey(searchKey));
        return object.toJSONString();
    }

}
