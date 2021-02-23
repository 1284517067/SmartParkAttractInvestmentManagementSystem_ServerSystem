package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.PositionDao;
import com.rzh.iot.dao.UserDao;
import com.rzh.iot.model.User;
import com.rzh.iot.service.DepartmentService;
import com.rzh.iot.service.PositionService;
import com.rzh.iot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    PositionDao positionDao;
    @Autowired
    PositionService positionService;
    @Autowired
    DepartmentService departmentService;


    @Override
    public HashMap<String, Object> loginVerify(User user) {
        String msg = null;
        boolean flag = false;
        HashMap<String,Object>map = new HashMap<>();
        try{
            if (userDao.isExistUsername(user) == 1){
                if (userDao.verifyPassword(user) == 1) {
                    String status = userDao.getStatusByUsername(user);
                    switch (status){
                        case "启用" :
                            flag = true;
                            msg = "验证成功";
                            user = userDao.getUserByUsername(user);
                            map.put("userInfo",user);
                            break;
                        case "锁定" :
                            flag = false;
                            msg = "账号已锁定，请联系管理员";
                            break;
                        case "注销" :
                            flag = false;
                            msg = "账号已注销";
                            break;
                        case "禁用" :
                            flag = false;
                            msg = "账号已禁用，请联系管理员";
                        default:
                            break;
                    }
                }else {
                    flag = false;
                    msg = "密码错误";
                }
            }else {
                flag = false;
                msg = "用户名不存在";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("flag",flag);
        map.put("msg",msg);
        return map;
    }

    @Override
    public Long getPositionIdByUsername(String username) {
        return userDao.getPositionIdByUsername(username);
    }

    @Override
    public List<User> getUserListBylimit(Integer currentPage, Integer limit) {
        List<User> users = userDao.getUserListByLimit((currentPage - 1) * limit , limit);
        for (User user:users){
            user.setPositionName(positionService.getPositionNameById(user.getPositionId()));
            user.setDepartmentName(departmentService.getDepartmentNameByPositionId(user.getPositionId()));
        }
        return users;
    }

    @Override
    public int getUserCount() {
        return userDao.getUserCount();
    }

    @Override
    public HashMap<String, String> updateUser(User user,String type) {
        HashMap<String,String> map = new HashMap<>();
        System.out.println(type);
        switch (type){
            case "新增":
                if (userDao.isExistUsername(user) != 0){
                    map.put("responseCode","400");
                    map.put("msg","该用户名已存在");
                    return map;
                }
                user.setPassword("123");
                userDao.createUser(user);
                break;
            case "更新":
                if (userDao.isExistUsername(user) == 0){
                    map.put("responseCode","400");
                    map.put("msg","该用户名不存在");
                    return map;
                }
                System.out.println("user:"+user.toString());
                userDao.updateUser(user);
                break;
        }
        map.put("responseCode","200");
        map.put("msg","保存成功");
        return map;
    }

    @Override
    public HashMap<String, String> switchUserStatus(String username, String status) {
        HashMap<String,String> map = new HashMap<>();
        userDao.updateUserStatus(username,status);
        map.put("responseCode","200");
        map.put("msg",status+"成功");
        return map;
    }

    @Override
    public HashMap<String, String> unsubscribeUser(String username) {
        HashMap<String,String> map = new HashMap<>();
        userDao.updateUserStatus(username,"注销");
        map.put("responseCode", "200");
        map.put("msg","注销账号成功");
        return map;
    }

    @Override
    public HashMap<String, String> refreshPassword(String username) {
        HashMap<String,String> map = new HashMap<>();
        User user = new User();
        user.setUsername(username);
        user.setPassword("123");
        userDao.updateUserPassword(user);
        map.put("responseCode","200");
        map.put("msg","重置密码成功");
        return map;
    }

    @Override
    public List<User> getUserListByKey(String key) {
        return userDao.getUserListByKey(key);
    }

    @Override
    public int getCountOfUserByKey(String key) {
        return userDao.getCountOfUserByKey(key);
    }

    @Override
    public JSONObject getUserListByPosition(Long departmentId) {
        JSONObject object = new JSONObject();
        Long positionId = positionDao.getPositionIdByDepartmentIdAndPositionName(departmentId,"经理");
        if (positionId == null){
            object.put("responseCode",400);
            object.put("msg","该部门没有此职位");
            return object;
        }
        object.put("responseCode",200);
        object.put("managers",userDao.getUsernameByPositionId(positionId));
        return object;
    }

    @Override
    public List<String> getUsersByPositionId(Long positionId) {

        return userDao.getUsernameByPositionId(positionId);
    }

    @Override
    public JSONObject getUsernameList() {
        JSONObject object = new JSONObject();
        object.put("responseCode", 200);
        object.put("userList",userDao.getUsernameList());
        return object;
    }

}
