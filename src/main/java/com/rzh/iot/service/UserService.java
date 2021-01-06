package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    HashMap<String,Object> loginVerify(User user);

    long getPositionIdByUsername(String username);

    List<User> getUserListBylimit(Integer currentPage, Integer limit);

    int getUserCount();

    HashMap<String,String> updateUser(User user,String type);

    HashMap<String,String> switchUserStatus(String username , String status);

    HashMap<String,String> unsubscribeUser(String username);

    HashMap<String,String> refreshPassword(String username);

    List<User> getUserListByKey(String key);

    int getCountOfUserByKey(String key);

    JSONObject getUserListByPosition(Long departmentId);
}
