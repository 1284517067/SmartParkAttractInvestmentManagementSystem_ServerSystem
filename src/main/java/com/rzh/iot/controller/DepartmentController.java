package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.service.DepartmentService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/getDepartmentListData")
    @JwtToken
    @ResponseBody
    public String getDepartmentListData(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("positionArrayData",departmentService.getDepartmentListData());
        return object.toJSONString();
    }

    @RequestMapping(value = "/getDepartmentTreeData")
    @JwtToken
    @ResponseBody
    public String getDepartmentTreeData(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("departmentTree",departmentService.getDepartmentTreeData());
        return object.toJSONString();
    }

    @RequestMapping(value = "/getDepartmentTableData")
    @JwtToken
    @ResponseBody
    public String getDepartmentTableData(@RequestParam String currentPage , @RequestParam String limit){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("positionArrayData",departmentService.getDepartmentListByLimit(Integer.parseInt(currentPage),Integer.parseInt(limit)));
        return object.toJSONString();
    }

    @RequestMapping(value = "/createDepartment")
    @JwtToken
    @ResponseBody
    public String updateDepartment(@RequestParam String departmentId,@RequestParam String departmentName,@RequestParam String submitType){
        JSONObject object = new JSONObject();
        HashMap<String,String> map = departmentService.updateDepartment(departmentId,departmentName,submitType);
        object.put("responseCode",Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getDepartmentTotal")
    @JwtToken
    @ResponseBody
    public String getDepartmentTotal(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("dataTotal",departmentService.getDepartmentTotal());
        return object.toJSONString();
    }

    @RequestMapping(value = "/deleteDepartmentByDepartmentId")
    @JwtToken
    @ResponseBody
    public String deleteDepartmentByDepartmentId(@RequestParam String departmentId){
        JSONObject object = new JSONObject();
        if (departmentService.deleteDepartmentByDepartmentId(Long.parseLong(departmentId)) == 0){
            object.put("responseCode",400);
            object.put("msg","删除失败");
        }else {
            object.put("responseCode",200);
            object.put("msg","删除成功");
        }
        return object.toJSONString();
    }

    @RequestMapping(value = "/getDepartmentDataByKey")
    @JwtToken
    @ResponseBody
    public String getDepartmentDataByKey(@RequestParam String searchKey){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("tableData",departmentService.getDepartmentListByKey(searchKey));
        object.put("dataTotal",departmentService.getDepartmentCountByKey(searchKey));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getDepartmentByPositionId")
    @JwtToken
    @ResponseBody
    public String getDepartmentByPositionId(@RequestParam String positionId){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        Long result = departmentService.getDepartmentIdByPositionId(Long.parseLong(positionId));
        System.out.println(result);
        object.put("departmentId",result);
        return object.toJSONString();
    }
}
