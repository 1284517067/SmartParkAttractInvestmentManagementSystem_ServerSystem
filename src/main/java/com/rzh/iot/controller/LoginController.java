package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.rzh.iot.model.User;
import com.rzh.iot.service.MenuService;
import com.rzh.iot.service.UserService;
import com.rzh.iot.utils.JwtToken;
import com.rzh.iot.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(@RequestParam String username,@RequestParam String password) {
        String result = null;
        JSONObject object = new JSONObject();
        System.out.println(username + " " + password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HashMap<String,Object> verify = userService.loginVerify(user);
        boolean flag = (boolean) verify.get("flag");
        if (flag){
            String token = TokenUtil.sign(user);
            object.put("token",token);
            object.put("userInfo",(User)verify.get("userInfo"));
            object.put("responseCode",200);
        }else {
            object.put("responseCode", 400);
            object.put("msg",(String)verify.get("msg"));
        }
        result = object.toJSONString();
        return result;
    }

}
