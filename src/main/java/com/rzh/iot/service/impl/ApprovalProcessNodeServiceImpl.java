package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.ApprovalProcessDao;
import com.rzh.iot.dao.ApprovalProcessNodeDao;
import com.rzh.iot.model.ApprovalProcess;
import com.rzh.iot.model.ApprovalProcessNode;
import com.rzh.iot.service.ApprovalProcessNodeService;
import com.rzh.iot.service.ApprovalProcessService;
import com.rzh.iot.service.DepartmentService;
import com.rzh.iot.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class ApprovalProcessNodeServiceImpl implements ApprovalProcessNodeService {

    @Autowired
    ApprovalProcessDao approvalProcessDao;

    @Autowired
    ApprovalProcessNodeDao approvalProcessNodeDao;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    PositionService positionService;

    @Override
    public List<ApprovalProcessNode> getApprovalProcessNodeData(Long approvalProcessId) {
        List<ApprovalProcessNode> list = approvalProcessNodeDao
                .getApprovalProcessNodeListByApprovalProcessId(approvalProcessId);
        LinkedList<ApprovalProcessNode> data = new LinkedList<>();
        for (ApprovalProcessNode item : list){
            item.setDepartmentName(departmentService.getDepartmentNameByPositionId(item.getPositionId()));
            item.setPositionName(positionService.getPositionNameById(item.getPositionId()));
            if (item.getPrevNodeId() == null){
                data.add(item);
            }
        }
        int size = data.size();
        for (int i = 0 ; i < size ; i++){
            for (ApprovalProcessNode next : list){
                if (next.getPrevNodeId() != null && next.getPrevNodeId() == data.getLast().getApprovalProcessNodeId()){
                    data.add(next);
                    size++;
                }
            }
        }
        return data;
    }

    @Override
    public HashMap<String, String> updateApprovalProcessNode(String approvalProcessId, String nodes , String submitType) {
        HashMap<String,String> map = new HashMap<>();
        List<ApprovalProcessNode> list = mountApprovalProcessNode(approvalProcessId,nodes);
        switch (submitType){
            case "新增":
                if (approvalProcessNodeDao.isApprovalProcessExist(Long.parseLong(approvalProcessId)) != 0){
                    map.put("responseCode","400");
                    map.put("msg","该审批流已存在审批节点");
                    return map;
                }
                createApprovalProcessNode(list);
                map.put("responseCode","200");
                map.put("msg","保存成功");
                break;
            case "更新":
                if (approvalProcessNodeDao.isApprovalProcessExist(Long.parseLong(approvalProcessId)) == 0) {
                    map.put("responseCode","400");
                    map.put("msg","该审批流的节点已不存在");
                    return map;
                }
                approvalProcessNodeDao.deleteApprovalProcessNodeByApprovalProcessId(Long.parseLong(approvalProcessId));
                createApprovalProcessNode(list);
                map.put("responseCode","200");
                map.put("msg","更新成功");
                break;
        }

        return map;
    }

    @Override
    public List<ApprovalProcess> getApprovalProcessNodeListData(Integer currentPage,Integer limit) {
        List<ApprovalProcess> list = approvalProcessNodeDao.getApprovalProcessList((currentPage - 1)*limit,limit);
        List<ApprovalProcessNode> nodes = approvalProcessNodeDao.getApprovalProcessNodeList();
        for(ApprovalProcessNode node : nodes){
            node.setPositionName(positionService.getPositionNameById(node.getPositionId()));
            node.setDepartmentName(departmentService.getDepartmentNameByPositionId(node.getPositionId()));
        }
        for (ApprovalProcess item : list){
            List<ApprovalProcessNode> nodeList = new ArrayList<>();
            for (ApprovalProcessNode node : nodes){
                if (item.getApprovalProcessId().equals(node.getApprovalProcessId())){
                    nodeList.add(node);
                }
            }
            item.setNodes(sortApprovalProcessNodeList(nodeList));
        }
        return list;
    }

    @Override
    public int getApprovalProcessCount() {

        return approvalProcessNodeDao.getApprovalProcessCount();
    }

    @Override
    public HashMap<String, String> deleteApprovalProcessNodeByApprovalProcessId(Long approvalProcessId) {
        HashMap<String,String> map = new HashMap<>();
        int result = approvalProcessNodeDao.deleteApprovalProcessNodeByApprovalProcessId(approvalProcessId);
        if (result == 0){
            map.put("responseCode","400");
            map.put("msg","删除失败");
            return map;
        }
        map.put("responseCode","200");
        map.put("msg","删除成功");
        return map;
    }

    @Override
    public HashMap<String, Object> getApprovalProcessNodeListByKey(String key) {
        HashMap<String,Object> map = new HashMap<>();
        List<ApprovalProcess> list = approvalProcessNodeDao.getApprovalProcessListByKey(key);
        if (list.size() == 0 ){
            map.put("responseCode",400);
            map.put("msg","暂无数据");
            return map;
        }
        List<ApprovalProcessNode> nodes = approvalProcessNodeDao.getApprovalProcessNodeList();
        for (ApprovalProcessNode node:nodes){
            node.setDepartmentName(departmentService.getDepartmentNameByPositionId(node.getPositionId()));
            node.setPositionName(positionService.getPositionNameById(node.getPositionId()));
        }

        list = mountApprovalProcessData(list,nodes);
        map.put("responseCode",200);
        map.put("dataList",list);
        map.put("msg","查询成功");
        return map;
    }

    @Override
    public int getCountOfApprovalProcessNodeByKey(String key) {
        return approvalProcessNodeDao.getCountOfApprovalProcessByKey(key);
    }

    @Override
    public JSONObject getApprovalProcessNodesByContractType(String contractType) {
        JSONObject object = new JSONObject();
        Long approvalProcessId = approvalProcessDao.getActiveApprovalProcessIdByContractType(contractType);
        if (approvalProcessId == null){
            return null;
        }
        object.put("responseCode",200);
        object.put("approvalProcess",getApprovalProcessNodeData(approvalProcessId));
        return object;
    }

    public List<ApprovalProcessNode> mountApprovalProcessNode(String approvalProcessId,String nodes){
        JSONArray nodeData = JSONArray.parseArray(nodes);
        List<ApprovalProcessNode> list = new ArrayList<>();
        for (int i = 0 ; i < nodeData.size() ; i++){
            JSONObject object = nodeData.getJSONObject(i);
            Object positionIds = JSONObject.parse(object.getString("positionId"));
            Long positionId = null;
            if (positionIds instanceof Integer){
                positionId = ((Integer) positionIds).longValue();
            }else {
                positionId = ((JSONArray) positionIds).getLong(((JSONArray) positionIds).size() - 1);

            }
            ApprovalProcessNode node = new ApprovalProcessNode();
            node.setApprovalProcessId(Long.parseLong(approvalProcessId));
            node.setApprovalProcessNodeName(object.getString("approvalProcessNodeName"));
            node.setPositionId(positionId);
            list.add(node);
        }
        return list;
    }

    public void createApprovalProcessNode(List<ApprovalProcessNode> list){
        for (int i = 0 ; i < list.size() ; i++){
            if (i != 0){
                list.get(i).setPrevNodeId(list.get(i-1).getApprovalProcessNodeId());
            }
            approvalProcessNodeDao.createApprovalProcessNode(list.get(i));
        }
    }

    public List<ApprovalProcessNode> sortApprovalProcessNodeList(List<ApprovalProcessNode> list){
        List<ApprovalProcessNode> nodes = new ArrayList<>();
        for (ApprovalProcessNode item : list){
            if (item.getPrevNodeId() == null){
                nodes.add(item);
            }
        }
        int size = nodes.size();
        for (int i = 0 ; i < size; i++){
            for (ApprovalProcessNode item : list){
                if (item.getPrevNodeId() != null){
                    if (item.getPrevNodeId().equals(nodes.get(i).getApprovalProcessNodeId())){
                        nodes.add(item);
                        size++;
                    }
                }
            }
        }
        return nodes;
    }

    private List<ApprovalProcess> mountApprovalProcessData(List<ApprovalProcess> list , List<ApprovalProcessNode> nodes){
        for (ApprovalProcess item : list){
            List<ApprovalProcessNode> nodeList = new ArrayList<>();
            for (ApprovalProcessNode node :nodes){
                if (node.getApprovalProcessId().equals(item.getApprovalProcessId())){
                    nodeList.add(node);
                }
            }
            item.setNodes(sortApprovalProcessNodeList(nodeList));
        }
        return list;
    }
}
