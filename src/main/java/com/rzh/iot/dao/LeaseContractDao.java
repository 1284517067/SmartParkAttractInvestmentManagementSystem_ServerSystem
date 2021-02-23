package com.rzh.iot.dao;

import com.rzh.iot.model.LeaseContract;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LeaseContractDao {

    @Select("select a.form_id, a.form_name, a.enterprise_id, b.enterprise_name, a.sign_date, a.start_date," +
            " a.expiry_date, a.status, a.applicant from lease_contract a inner join enterprise b where" +
            " a.status = #{status} and a.enterprise_id = b.enterprise_id order by a.form_id desc limit #{currentPage}, #{limit}")
    List<LeaseContract> getLeaseContractTableData(Integer currentPage, Integer limit, String status);

    @Select("select count(*) from lease_contract where" +
            " status = #{status}")
    int getSizeOfLeaseContractTable(String status);

    @Select("select a.*, b.enterprise_name from lease_contract a inner join enterprise b where a.enterprise_id = b.enterprise_id and form_id = #{formId}")
    LeaseContract getLeaseContractFormData(Long formId);

    @Insert("insert into lease_contract (form_name, enterprise_id, start_date, expiry_date, " +
            "deposit, warning_date, status, approval_status, reset_flag, sign_date, applicant, apply_date)" +
            " values " +
            "(#{formName}, #{enterpriseId}, #{startDate}, #{expiryDate}, #{deposit}, #{warningDate}, #{status}, #{approvalStatus}, #{resetFlag}, " +
            "#{signDate}, #{applicant}, #{applyDate})")
    @Options(useGeneratedKeys = true,keyProperty = "formId", keyColumn = "form_id")
    int createLeaseContract(LeaseContract leaseContract);

    @Update("update lease_contract set form_name = #{formName}, enterprise_id = #{enterpriseId}, start_date = #{startDate}, " +
            "expiry_date = #{expiryDate}, deposit = #{deposit}, warning_date = #{warningDate}, status = #{status}, approval_status = #{approvalStatus}, " +
            "reset_flag = #{resetFlag}, sign_date = #{signDate}, applicant = #{applicant}, apply_date = #{applyDate} " +
            "where form_id = #{formId}")
    int updateLeaseContract(LeaseContract leaseContract);

    @Update("update lease_contract set status = #{status} where form_id = #{formId}")
    int updateLeaseContractStatusByFormId(Long formId, String status);

    @Update("update lease_contract set approval_status = #{approvalStatus} where form_id = #{formId}")
    int updateLeaseContractApprovalStatusByFormId(Long formId, String approvalStatus);

    @Select("select count(*) from lease_contract where form_name = #{formName}")
    int isNameExist(String formName);

    @Select("select count(*) from lease_contract where form_id = #{formId}")
    int isFormExist(Long formId);

    @Select("select * from lease_contract where status = '已发' and approval_status = '审批完成' and reset_flag = 0")
    List<LeaseContract> getExecutingLeaseContract();

    @Update("update lease_contract set reset_flag = #{resetFlag} where form_id = #{formId}")
    int updateLeaseContractResetFlag(Long formId, Integer resetFlag);

    @Select("select distinct a.form_id, a.form_name, a.enterprise_id, b.enterprise_name, a.sign_date, a.start_date," +
            " a.expiry_date, a.status, a.applicant from lease_contract a inner join enterprise b where" +
            " a.status = #{status} and a.enterprise_id = b.enterprise_id and " +
            "((a.form_id like '%${value}%') or (a.form_name like '%${value}%') or (a.enterprise_id like '%${value}%') " +
            "or (b.enterprise_name like '%${value}%') or (a.sign_date like '%${value}%') or (a.start_date like '%${value}%') " +
            "or (a.expiry_date like '%${value}%') or (a.applicant like '%${value}%'))" +
            " order by a.form_id desc")
    List<LeaseContract> searchLeaseContractByKey(String value, String status);

    @Select("select count(distinct a.form_id) from lease_contract a inner join enterprise b where" +
            " a.status = #{status} and a.enterprise_id = b.enterprise_id and " +
            "((a.form_id like '%${value}%') or (a.form_name like '%${value}%') or (a.enterprise_id like '%${value}%') " +
            "or (b.enterprise_name like '%${value}%') or (a.sign_date like '%${value}%') or (a.start_date like '%${value}%') " +
            "or (a.expiry_date like '%${value}%') or (a.applicant like '%${value}%'))")
    int getSizeOfSearchLeaseContractByKey(String value, String status);


    @Select("select form_id, form_name from lease_contract where status = '已发' and approval_status = '审批完成' and enterprise_id = #{enterpriseId}")
    List<LeaseContract> getLeaseContractIdAndNameByEnterpriseId(Long enterpriseId);

}

