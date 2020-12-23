package com.rzh.iot.service.impl;

import com.rzh.iot.dao.ApprovalProcessDao;
import com.rzh.iot.model.ApprovalProcess;
import com.rzh.iot.service.ApprovalProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service

public class ApprovalProcessServiceImpl implements ApprovalProcessService {

    @Autowired
    ApprovalProcessDao approvalProcessDao;
    @Override
    public List<ApprovalProcess> getApprovalProcessByLimit(Integer currentPage, Integer limit) {
        List<ApprovalProcess> list = approvalProcessDao.getApprovalProcessesByLimit((currentPage - 1)*limit,limit);
        return list;
    }

    @Override
    public Integer getApprovalProcessTotal() {

        return approvalProcessDao.getApprovalProcessTotal();
    }

    @Override
    public HashMap<String,String> updateApprovalProcess(ApprovalProcess approvalProcess) {
        HashMap<String,String> map = new HashMap<>();
        int result = 0;
        String type;
        if (approvalProcess.getApprovalProcessId() == null){
            if (!isDuplication(approvalProcess)){
                map.put("code","400");
                map.put("msg","该合同类型及业务类型已存在启用的审批流，请先废弃当前正在启用的审批流");
                return map;
            }

            if (!isNameInvoke(approvalProcess)){
                map.put("code","400");
                map.put("msg","审批流名称重复");
                return map;
            }

            type = "保存";
            approvalProcess.setStatus("启用");
            result = approvalProcessDao.createApprovalProcess(approvalProcess);

        }else {
            type = "更新";
            if (approvalProcessDao.isApprovalProcessExist(approvalProcess.getApprovalProcessId()) == 0){
                map.put("code","400");
                map.put("msg","审批流不存在");
                return map;
            }
            if (approvalProcessDao.isNameInvoke(approvalProcess) > 1){
                map.put("code","400");
                map.put("msg","审批流名称重复");
                return map;
            }
            result = approvalProcessDao.updateApprovalProcess(approvalProcess);
        }
        if (result == 1){
            map.put("code","200");
            map.put("msg",type+"成功");
        }else {
            map.put("code","400");
            map.put("msg",type+"失败");
        }
        return map;
    }

    @Override
    public List<ApprovalProcess> getApprovalProcessByKey(String key) {
        return approvalProcessDao.getApprovalProcessByKey(key);
    }

    @Override
    public boolean isDuplication(ApprovalProcess approvalProcess) {
        int result = approvalProcessDao.getCountOfTheSameTypeApprovalProcess(approvalProcess);
        if (result == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean changeApprovalProcessStatus(Long approvalProcessId, String status) {
        boolean flag = false;
        int result = approvalProcessDao.changeApprovalProcessStatus(approvalProcessId,status);

        if (result == 1){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean isInvoke(String contractType, String businessType) {
        if (approvalProcessDao.getCountOfInvoke(contractType,businessType) == 0){
            return true;
        }
        return false;
    }

    @Override
    public List<ApprovalProcess> getApprovalProcessIdAndApprovalProcessName() {
        return approvalProcessDao.getApprovalProcessIdAndApprovalProcessName();
    }

    @Override
    public HashMap<String, String> deleteApprovalProcessByApprovalProcessId(Long approvalProcessId) {
        HashMap<String,String> map = new HashMap<>();
        int result = approvalProcessDao.deleteApprovalProcessByApprovalProcessId(approvalProcessId);
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
    public int getCountOfApprovalProcessByKey(String key) {
        return approvalProcessDao.getCountOfApprovalProcessByKey(key);
    }

    public boolean isNameInvoke(ApprovalProcess approvalProcess){
        if (approvalProcessDao.isNameInvoke(approvalProcess) == 0){
            return true;
        }
        return false;
    }

}
