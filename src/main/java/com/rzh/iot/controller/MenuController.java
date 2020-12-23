package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.MenuNode;
import com.rzh.iot.service.MenuService;
import com.rzh.iot.service.UserService;
import com.rzh.iot.utils.JwtToken;
import com.rzh.iot.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class MenuController {

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/getMenu")
    @ResponseBody
    @JwtToken
    public String getMenu(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization");
        System.out.println("getMenu - token: " + token);
        String username = TokenUtil.getUserId(token);
        Long positionId = userService.getPositionIdByUsername(username);
        List<MenuNode> menuTree = menuService.getUserMenuTree(positionId);
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("menuTree",menuTree);
        return object.toJSONString();
    }

    @RequestMapping(value = "/getMenuTree")
    @JwtToken
    @ResponseBody
    public String getMenuTree(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("menuTree",menuService.getMenuTreeData());
        return object.toJSONString();
    }

    @RequestMapping(value = "/getAllMenuTree")
    @JwtToken
    @ResponseBody
    public String getAllMenuTree(){
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("menuTree",menuService.getAllMenuTreeData());
        return object.toJSONString();
    }

    @RequestMapping(value = "/getMenuList")
    @JwtToken
    @ResponseBody
    public String getMenuList(){
        JSONObject object = new JSONObject();
        object.put("responseCode", 200);
        object.put("menuList",menuService.getMenuList());
        return object.toJSONString();
    }

    @RequestMapping(value = "/updateMenu")
    @JwtToken
    @ResponseBody
    public String updateMenu(@RequestBody MenuNode menuNode){
        JSONObject object = new JSONObject();
        HashMap<String,String> map = menuService.updateMenuNode(menuNode);
        object.put("responseCode", Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/deleteMenu")
    @JwtToken
    @ResponseBody
    public String deleteMenu(@RequestParam String menuId){
        JSONObject object = new JSONObject();
        HashMap<String,String> map = menuService.deleteMenu(Long.parseLong(menuId));
        object.put("responseCode",Integer.parseInt(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getMenuPrivileges")
    @JwtToken
    @ResponseBody
    public String getMenuPrivileges(@RequestParam String positionId){
        JSONObject object = new JSONObject();
        HashMap<String,Object> map = menuService.getMenuPrivileges(Long.parseLong(positionId));
        object.put("responseCode",map.get("responseCode"));
        object.put("msg",map.get("msg"));
        object.put("privileges",map.get("privileges"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/submitPrivileges")
    @JwtToken
    @ResponseBody
    public String submitPrivileges(@RequestParam String positionId,@RequestParam String privileges){
        JSONObject object = new JSONObject();
        JSONArray array = JSONArray.parseArray(privileges);
        List<Long> list = array.toJavaList(Long.class);
        HashMap<String,String> map = menuService.updateMenuPrivileges(Long.parseLong(positionId),list);
        object.put("responseCode",Long.parseLong(map.get("responseCode")));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }
}
