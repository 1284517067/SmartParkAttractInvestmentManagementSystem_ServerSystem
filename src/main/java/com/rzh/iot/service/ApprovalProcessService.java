package com.rzh.iot.service;

import com.rzh.iot.model.ApprovalProcess;

import java.util.HashMap;
import java.util.List;

public interface ApprovalProcessService {
    List<ApprovalProcess> getApprovalProcessByLimit(Integer currentPage,Integer limit);

    Integer getApprovalProcessTotal();

    HashMap<String,String> updateApprovalProcess(ApprovalProcess approvalProcess);

    List<ApprovalProcess> getApprovalProcessByKey(String key);

    boolean isDuplication(ApprovalProcess approvalProcess);

    boolean changeApprovalProcessStatus(Long approvalProcessId,String status);

    boolean isInvoke(String contractType,String businessType);

    List<ApprovalProcess> getApprovalProcessIdAndApprovalProcessName();

    HashMap<String,String> deleteApprovalProcessByApprovalProcessId(Long approvalProcessId);

    int getCountOfApprovalProcessByKey(String key);
}
