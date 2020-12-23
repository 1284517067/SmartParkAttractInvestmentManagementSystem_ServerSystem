package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.ProjectDao;
import com.rzh.iot.model.Project;
import com.rzh.iot.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDao projectDao;

    @Override
    public HashMap<String,Object> getProjectListByLimit(Integer currentPage, Integer limit) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("responseCode",200);
        map.put("tableData",projectDao.getProjectListByLimit((currentPage - 1)*limit,limit));
        map.put("total",projectDao.getSizeOfProjectList());
        return map;
    }

    @Override
    public HashMap<String, Object> getProjectByProjectId(Long projectId) {
        HashMap<String,Object> map = new HashMap<>();
        if (projectDao.isProjectExist(projectId) == 0){
            map.put("responseCode",400);
            map.put("msg","该项目不存在");
            return map;
        }
        map.put("responseCode", 200);
        map.put("projectData",projectDao.getProjectByProjectId(projectId));
        return map;
    }

    @Override
    public HashMap<String, Object> updateProject(Project project) {
        HashMap<String,Object> map = new HashMap<>();
        if (project.getProjectId() == null){
            /**
             * 新增
             * */
            if (projectDao.isEnterpriseNameExist(project.getEnterpriseName()) != 0){
                map.put("responseCode",400);
                map.put("msg","该企业名称已存在");
                return map;
            }
            project.setStatus("正常");
            projectDao.createProject(project);
            map.put("responseCode",200);
            map.put("msg","保存成功");
        }else {
            /**
             * 更新
             * */
            if (projectDao.isProjectExist(project.getProjectId()) == 0){
                map.put("responseCode",400);
                map.put("msg","该项目不存在");
                return map;
            }
            if (projectDao.isEnterpriseNameExist(project.getEnterpriseName()) > 1){
                map.put("responseCode",400);
                map.put("msg","该企业名称已存在");
                return map;
            }
            projectDao.updateUpdate(project);
            map.put("responseCode",200);
            map.put("msg","保存成功");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> deleteProject(Long projectId) {
        HashMap<String,Object> map = new HashMap<>();
        if (projectDao.isProjectExist(projectId) == 0){
            map.put("responseCode",400);
            map.put("msg","该项目不存在");
            return map;
        }
        projectDao.deleteProject(projectId);
        map.put("responseCode",200);
        map.put("msg","删除成功");
        return map;
    }

    @Override
    public HashMap<String, Object> handleProject(Long projectId, String username) {
        HashMap<String,Object> map = new HashMap<>();
        if (projectDao.isProjectExist(projectId) == 0){
            map.put("responseCode",400);
            map.put("msg","该项目不存在");
            return map;
        }
        projectDao.handleProject(projectId,username);
        map.put("responseCode",200);
        map.put("msg","认领成功");
        return map;
    }

    @Override
    public JSONObject getProjectListByKey(String key) {
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        int size = projectDao.getSizeOfProjectListByKey(key);
        object.put("total",size);
        if (size == 0){
            object.put("tableData",null);
            return object;
        }
        object.put("tableData",projectDao.getProjectListByKey(key));
        return object;
    }

    @Override
    public JSONObject getDeletedProjectListData(Integer currentPage, Integer limit) {
        JSONObject object = new JSONObject();
        int size = projectDao.getSizeOfDeletedProjectList();
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",projectDao.getDeletedProjectListByLimit((currentPage - 1)*limit , limit));
        return object;
    }

    @Override
    public JSONObject recoverProject(Long projectId) {
        JSONObject object = new JSONObject();
        if (projectDao.isProjectExist(projectId) == 0){
            object.put("responseCode",400);
            object.put("msg","该项目不存在");
            return object;
        }
        projectDao.recoverProjectByProjectId(projectId);
        object.put("responseCode",200);
        object.put("msg","恢复项目成功");
        return object;
    }

    @Override
    public JSONObject getDeletedProjectListByKey(String key) {
        JSONObject object = new JSONObject();
        int size = projectDao.getSizeOfDeletedProjectListByKey(key);
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",projectDao.getDeletedProjectListByKey(key));
        return object;
    }
}
