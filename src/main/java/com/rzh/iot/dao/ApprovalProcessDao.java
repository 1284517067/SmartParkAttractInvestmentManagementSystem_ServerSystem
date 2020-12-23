package com.rzh.iot.dao;

import com.rzh.iot.model.ApprovalProcess;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApprovalProcessDao {

    @Select("select approval_process_id ,approval_process_name, contract_type, process_description , business_type , status from approval_process where status != '已删除' order by approval_process_id desc limit #{currentPage} , #{limit}")
    List<ApprovalProcess> getApprovalProcessesByLimit(Integer currentPage,Integer limit);

    @Select("select count(*) from approval_process where status != '已删除'")
    Integer getApprovalProcessTotal();

    @Insert("insert into approval_process (approval_process_name , contract_type, business_type , process_description , status) values (#{approvalProcessName} , #{contractType} , #{businessType} , #{processDescription} , #{status})")
    int createApprovalProcess(ApprovalProcess approvalProcess);

    @Update("update approval_process set approval_process_name = #{approvalProcessName} , contract_type = #{contractType} , business_type = #{businessType} , process_description = #{processDescription} where approval_process_id = #{approvalProcessId}")
    int updateApprovalProcess(ApprovalProcess approvalProcess);

    @Select("select approval_process_id , approval_process_name , contract_type , business_type , process_description , status from approval_process where status <> '已删除' and ((approval_process_id like '%${value}%') or (approval_process_name like '%${value}%') or (contract_type like '%${value}%') or (business_type like '%${value}%') or (process_description like '%${value}%')) order by approval_process_id desc")
    List<ApprovalProcess> getApprovalProcessByKey(String key);

    @Select("select count(distinct approval_process_id) from approval_process where status <> '已删除' and ((approval_process_id like '%${value}%') or (approval_process_name like '%${value}%') or (contract_type like '%${value}%') or (business_type like '%${value}%') or (process_description like '%${value}%')) order by approval_process_id desc")
    int getCountOfApprovalProcessByKey(String key);

    @Select("select count(*) from approval_process where contract_type = #{contractType} and business_type = #{businessType} and status = '启用'")
    int getCountOfTheSameTypeApprovalProcess(ApprovalProcess approvalProcess);

    @Update("update approval_process set status = #{status} where approval_process_id = #{approvalProcessId}")
    int changeApprovalProcessStatus(Long approvalProcessId,String status);

    @Select("select count(*) from approval_process where contract_type = #{contractType} and business_type = #{businessType} and status = '启用'")
    int getCountOfInvoke(String contractType,String businessType);

    @Select("select count(*) from approval_process where approval_process_name = #{approvalProcessName}")
    int isNameInvoke(ApprovalProcess approvalProcess);

    @Select("select * from approval_process where contract_type = #{contractType} and business_type = #{businessType}")
    List<ApprovalProcess> getDuplicationList(String contractType,String businessType);

    @Select("select approval_process_id , approval_process_name from approval_process where status != '已删除'")
    List<ApprovalProcess> getApprovalProcessIdAndApprovalProcessName();

    @Update("update approval_process set status = '已删除' where approval_process_id = #{approvalProcessId}")
    int deleteApprovalProcessByApprovalProcessId(Long ApprovalProcessId);

    @Select("select count(*) form approval_process where approval_process_id = #{approvalProcessId} and status != '已删除'")
    int isApprovalProcessExist(Long approvalProcessId);

}
