package com.rzh.iot.service;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.Project;

import java.util.HashMap;
import java.util.List;

public interface ProjectService {

    HashMap<String,Object> getProjectListByLimit(Integer currentPage, Integer limit);

    HashMap<String,Object> getProjectByProjectId(Long projectId);

    HashMap<String,Object> updateProject(Project project);

    HashMap<String,Object> deleteProject(Long projectId);

    HashMap<String,Object> handleProject(Long projectId,String username);

    JSONObject getProjectListByKey(String key);

    JSONObject getDeletedProjectListData(Integer currentPage,Integer limit);

    JSONObject recoverProject(Long projectId);

    JSONObject getDeletedProjectListByKey(String key);
}
