package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.User;
import com.rzh.iot.service.UserService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class UserManagementController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getUserListByLimit")
    @JwtToken
    @ResponseBody
    public String getUserListByLimit(@RequestParam String currentPage , @RequestParam String limit){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("tableData",userService.getUserListBylimit(Integer.parseInt(currentPage),Integer.parseInt(limit)));
        object.put("total",userService.getUserCount());
        return object.toJSONString();
    }

    @RequestMapping(value = "/updateUser")
    @JwtToken
    @ResponseBody
    public String updateUser(@RequestParam String username, @RequestParam String status,@RequestParam String accountLevel , @RequestParam String positionId , @RequestParam String submitType){
        JSONObject object = new JSONObject();
        User user = new User();
        user.setUsername(username);
        user.setAccountLevel(Integer.parseInt(accountLevel));
        user.setStatus(status);
        Object positionIds = JSONObject.parse(positionId);
        Long position = null;
        if (positionIds instanceof Integer){
            position = ((Integer) positionIds).longValue();
        }else {
            position = ((JSONArray) positionIds).getLong(((JSONArray) positionIds).size() - 1);

        }
        user.setPositionId(position);
        HashMap<String,String> map = userService.updateUser(user,submitType);
        object.put("responseCode", Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/switchUserStatus")
    @JwtToken
    @ResponseBody
    public String switchUserStatus(@RequestParam String username,@RequestParam String status){
        JSONObject object = new JSONObject();
        HashMap<String,String> map = userService.switchUserStatus(username,status);
        object.put("responseCode",Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/unsubscribeUser")
    @JwtToken
    @ResponseBody
    public String unsubscribeUser(@RequestParam String username){
        JSONObject object = new JSONObject();
        HashMap<String,String> map = userService.unsubscribeUser(username);
        object.put("responseCode",Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/refreshPassword")
    @JwtToken
    @ResponseBody
    public String refreshPassword(@RequestParam String username){
        JSONObject object = new JSONObject();
        HashMap<String,String> map = userService.refreshPassword(username);
        object.put("responseCode",Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }
    @RequestMapping(value = "/searchUserByKey")
    @JwtToken
    @ResponseBody
    public String searchUserByKey(@RequestParam String searchKey){
        JSONObject object = new JSONObject();
        List<User> users = userService.getUserListByKey(searchKey);
        if (users.size() == 0){
           object.put("msg","暂无数据");
        }else {
            object.put("msg","查询成功");
            object.put("userData",users);
        }
        object.put("responseCode",200);
        object.put("total",userService.getCountOfUserByKey(searchKey));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getUserListByPosition")
    @JwtToken
    @ResponseBody
    public String getUserListByPosition(@RequestParam String departmentId){
        return userService.getUserListByPosition(Long.parseLong(departmentId)).toJSONString() ;
    }
}
