package com.rzh.iot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rzh.iot.dao.LeaseContractDao;
import com.rzh.iot.model.ApprovalOpinion;
import com.rzh.iot.model.LeaseContract;
import com.rzh.iot.model.Message;
import com.rzh.iot.service.*;
import com.rzh.iot.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Service
public class LeaseContractServiceImpl implements LeaseContractService {

    @Autowired
    LeaseContractDao leaseContractDao;

    @Autowired
    Common common;

    @Autowired
    LeaseContractRoomService leaseContractRoomService;

    @Autowired
    LeaseContractPayItemService leaseContractPayItemService;

    @Autowired
    ApprovalOpinionService approvalOpinionService;

    @Autowired
    MessageService messageService;

    @Override
    public JSONObject getLeaseContractTableData(Integer currentPage, Integer limit, String status) {
        JSONObject object = new JSONObject();
        int size = leaseContractDao.getSizeOfLeaseContractTable(status);
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",leaseContractDao.getLeaseContractTableData((currentPage - 1) * limit, limit, status));
        return object;
    }

    @Override
    public JSONObject getLeaseContractFormData(Long formId) {
        JSONObject object = new JSONObject();
        object.put("responseCode",200);
        object.put("form",leaseContractDao.getLeaseContractFormData(formId));
        return object;
    }

    /**
     * 保存租赁合同
     * */
    @Override
    public JSONObject saveLeaseContract(LeaseContract leaseContract) {
        return updateLeaseContract(leaseContract);
    }

    /**
     * 送办租赁合同
     * */
    @Override
    public JSONObject sendLeaseContract(LeaseContract leaseContract) {
        leaseContract.setStatus("已发");
        JSONObject object = saveLeaseContract(leaseContract);
        if ((int) object.get("responseCode") == 400 ){
            return object;
        }

        /**
         * 校验
         * */
        if (leaseContractDao.isFormExist(leaseContract.getFormId()) == 0){
            object.put("responseCode", 400);
            object.put("msg","该租赁合同不存在");
            return object;
        }

        /**
         *  根据启用审批流创建节点
         * */
        HashMap<String, Object> map  = approvalOpinionService.createApprovalOpinions(leaseContract.getFormId(),"租赁合同","新签");

        /**
         * 若无启用审批流，则默认审批完成
         * */
        if ((int) map.get("responseCode") == 404){
            leaseContractDao.updateLeaseContractApprovalStatusByFormId(leaseContract.getFormId(),"审批完成");
            object.put("responseCode",200);
            object.put("msg","送办成功");
            return object;
        }

        /**
         * 获取审批流里首个待审批节点
         * */
        ApprovalOpinion approvalOpinion = approvalOpinionService.getFirstWaitingApprovalOpinion(leaseContract.getFormId(),"租赁合同");
        if (approvalOpinion == null){
            /**
             * 若无审批节点，则视为审批完成
             * */
            leaseContractDao.updateLeaseContractApprovalStatusByFormId(leaseContract.getFormId(),"审批完成");
            object.put("responseCode",200);
            object.put("msg","送办成功");
            return object;
        }

        leaseContractDao.updateLeaseContractApprovalStatusByFormId(leaseContract.getFormId(),"等待" + approvalOpinion.getApprovalProcessNodeName());
        Message message = new Message();
        message.setFormId(leaseContract.getFormId());
        message.setMessage(leaseContract.getFormName() + "-" + approvalOpinion.getApprovalProcessNodeName());
        message.setApplicant(leaseContract.getApplicant());
        message.setPositionId(approvalOpinion.getPositionId());
        message.setType("待办");
        message.setStatus("正常");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        message.setCreateDate(format.format(new Date()));
        message.setContractType("租赁合同");

        HashMap<String,Object> map1 = messageService.updateMessage(message);
        if ((int) map1.get("responseCode") == 400){
            object.put("responseCode",map1.get("responseCode"));
            object.put("msg",map1.get("msg"));
            return object;
        }
        object.put("responseCode",200);
        object.put("msg","送办成功");
        return object;

    }

    @Override
    public JSONObject updateLeaseContract(LeaseContract leaseContract) {
        JSONObject object = new JSONObject();
        if (leaseContract.getFormId() == null){
            /**
             * 新增
             * */
            if (leaseContract.getStatus() != "已发"){
                leaseContract.setStatus("待发");
            }
            leaseContract.setResetFlag(new Integer(0));
            leaseContract.setFormName(common.mountFormName(leaseContract.getFormName(),"租赁合同"));
            leaseContractDao.createLeaseContract(leaseContract);
        }else {
            /**
             * 更新
             * */
            if (leaseContractDao.isFormExist(leaseContract.getFormId()) == 0){
                object.put("responseCode", 400);
                object.put("msg", "该租赁合同不存在");
                return object;
            }
            leaseContractDao.updateLeaseContract(leaseContract);
        }

        object = leaseContractRoomService.updateLeaseContractRooms(leaseContract.getFormId(),leaseContract.getSpaces());
        if ((int) object.get("responseCode") == 400){
            leaseContractRoomService.updateLeaseContractRooms(leaseContract.getFormId(),new ArrayList<>());
            return object;
        }

        object = leaseContractPayItemService.updateLeaseContractPayItem(leaseContract.getFormId(), leaseContract.getPayItems());
        if ((int) object.get("responseCode") == 400){
            leaseContractRoomService.updateLeaseContractRooms(leaseContract.getFormId(),new ArrayList<>());
            leaseContractPayItemService.updateLeaseContractPayItem(leaseContract.getFormId(),new ArrayList<>());
            return object;
        }

        object.put("responseCode",200);
        object.put("msg","保存成功");
        return object;
    }

    @Override
    public JSONObject deleteLeaseContract(Long formId) {
        JSONObject object = new JSONObject();
        if (leaseContractDao.isFormExist(formId) == 0){
            object.put("responseCode",400);
            object.put("msg","该记录不存在");
            return  object;
        }
        leaseContractDao.updateLeaseContractStatusByFormId(formId,"已删除");
        object.put("responseCode",200);
        object.put("msg","删除成功");
        return object;
    }

    @Override
    public JSONObject searchLeaseContractByKey(String key, String status) {
        JSONObject object = new JSONObject();
        int size = leaseContractDao.getSizeOfSearchLeaseContractByKey(key,status);
        object.put("responseCode",200);
        object.put("total",size);
        if (size == 0){
            return object;
        }
        object.put("tableData",leaseContractDao.searchLeaseContractByKey(key, status));
        return object;
    }


}
