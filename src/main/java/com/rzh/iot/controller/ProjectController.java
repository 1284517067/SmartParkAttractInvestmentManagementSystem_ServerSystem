package com.rzh.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.model.Project;
import com.rzh.iot.service.ProjectService;
import com.rzh.iot.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/getProjectTableData")
    @JwtToken
    @ResponseBody
    public String getProjectTableData(@RequestParam String currentPage, @RequestParam String limit){
        JSONObject object = new JSONObject();
        HashMap<String,Object> map = projectService.getProjectListByLimit(Integer.parseInt(currentPage),Integer.parseInt(limit));
        object.put("responseCode",map.get("responseCode"));
        object.put("tableData",map.get("tableData"));
        object.put("total",map.get("total"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getProjectDataByProjectId")
    @JwtToken
    @ResponseBody
    public String getProjectDataByProjectId(@RequestParam String projectId) {
        JSONObject object = new JSONObject();
        HashMap<String,Object> map = projectService.getProjectByProjectId(Long.parseLong(projectId));
        object.put("responseCode",map.get("responseCode"));
        object.put("projectData",map.get("projectData"));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/updateProject")
    @JwtToken
    @ResponseBody
    public String updateProject(@RequestBody String data){
        JSONObject object = new JSONObject();
        JSONObject form = JSONObject.parseObject(data);
        Project project = JSONObject.parseObject(form.getString("projectForm"),Project.class);
        HashMap<String,Object> map = projectService.updateProject(project);
        object.put("responseCode",map.get("responseCode"));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/deleteProject")
    @JwtToken
    @ResponseBody
    public String deleteProject(@RequestParam String projectId){
        JSONObject object = new JSONObject();
        HashMap<String,Object> map = projectService.deleteProject(Long.parseLong(projectId));
        object.put("responseCode",map.get("responseCode"));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/handleProject")
    @JwtToken
    @ResponseBody
    public String handleProject(@RequestParam String projectId,@RequestParam String user){
        JSONObject object = new JSONObject();
        HashMap<String,Object> map = projectService.handleProject(Long.parseLong(projectId),user);
        object.put("responseCode",map.get("responseCode"));
        object.put("msg",map.get("msg"));
        return object.toJSONString();
    }

    @RequestMapping(value = "/searchProjectByKey")
    @JwtToken
    @ResponseBody
    public String searchProjectByKey(@RequestParam String searchKey){
        JSONObject object = projectService.getProjectListByKey(searchKey);
        return object.toJSONString();
    }

    @RequestMapping(value = "/getDeletedProjectListData")
    @JwtToken
    @ResponseBody
    public String getDeletedProjectListData(@RequestParam String currentPage, @RequestParam String limit){
        JSONObject object = projectService.getDeletedProjectListData(Integer.parseInt(currentPage),Integer.parseInt(limit));
        return object.toJSONString();
    }

    @RequestMapping(value = "/recoverProject")
    @JwtToken
    @ResponseBody
    public String recoverProject(@RequestParam String projectId){
        JSONObject object = projectService.recoverProject(Long.parseLong(projectId));
        return object.toJSONString();
    }

    @RequestMapping(value = "/searchDeletedProjectByKey")
    @JwtToken
    @ResponseBody
    public String searchDeletedProjectByKey(@RequestParam String searchKey){
        JSONObject object = projectService.getDeletedProjectListByKey(searchKey);
        return object.toJSONString();
    }

    @RequestMapping(value = "/getPersonProjectTableData")
    @JwtToken
    @ResponseBody
    public String getPersonProjectTableData(@RequestParam String username , @RequestParam String limit ,@RequestParam String currentPage){
        JSONObject object = projectService.getPersonProjectList(username,Integer.parseInt(limit),Integer.parseInt(currentPage));
        return object.toJSONString();
    }

    @RequestMapping(value = "/getPersonProjectTableByKey")
    @JwtToken
    @ResponseBody
    public String getPersonProjectTableByKey(@RequestParam String username,@RequestParam String searchKey){
        JSONObject object = projectService.getPersonProjectTableByKey(username,searchKey);
        return object.toJSONString();
    }
}
