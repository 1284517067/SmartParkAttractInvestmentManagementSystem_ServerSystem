package com.rzh.iot.service;

import com.rzh.iot.model.ApprovalProcess;
import com.rzh.iot.model.ApprovalProcessNode;

import java.util.HashMap;
import java.util.List;

public interface ApprovalProcessNodeService {
    List<ApprovalProcessNode> getApprovalProcessNodeData(Long approvalProcessId);

    HashMap<String,String> updateApprovalProcessNode(String approvalProcessId , String nodes , String submitType);

    List<ApprovalProcess> getApprovalProcessNodeListData(Integer currentPage,Integer limit);

    int getApprovalProcessCount();

    HashMap<String,String> deleteApprovalProcessNodeByApprovalProcessId(Long ApprovalProcessId);

    HashMap<String,Object> getApprovalProcessNodeListByKey(String key);

    int getCountOfApprovalProcessNodeByKey(String key);
}
