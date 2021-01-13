package com.rzh.iot.dao;

import com.rzh.iot.model.IntentionAgreement;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IntentionAgreementDao {

    @Select("select a.form_id, a.enterprise_id, b.enterprise_name, b.contact, b.contact_tel, a.apply_date, a.deadline," +
            " a.approval_status from intention_agreement a inner join enterprise b where a.enterprise_id = b.enterprise_id" +
            " and a.status = #{status} order by a.form_id desc limit #{currentPage}, #{limit}")
    List<IntentionAgreement> getIntentionAgreementTableData(Integer currentPage,Integer limit, String status);

    @Select("select count(*) from intention_agreement" +
            " where status = #{status}")
    int getSizeOfIntentionAgreementTable(String status);

    @Select("select count(*) from intention_agreement where form_name = #{formName}")
    int isNameExist(String formName);

    @Insert("insert into intention_agreement (form_id, form_name, enterprise_id, earnest, deadline, " +
            "apply_date, applicant, remark, status, approval_status, reset_flag) values (#{formId}, #{formName}, " +
            "#{enterpriseId}, #{earnest}, #{deadline}, #{applyDate}, #{applicant}, #{remark}, #{status}, " +
            "#{approvalStatus}, #{resetFlag})")
    @Options(useGeneratedKeys = true,keyProperty = "formId", keyColumn = "form_id")
    int createIntentionAgreement(IntentionAgreement intentionAgreement);

    @Update("update intention_agreement set " +
            "form_name = #{formName}, enterprise_id = #{enterpriseId}, earnest = #{earnest}, deadline = #{deadline}, " +
            "apply_date = #{applyDate}, applicant = #{applicant}, remark = #{remark}, status = #{status}, " +
            "approval_status = #{approvalStatus} " +
            "where form_id = #{formId}")
    int updateIntentionAgreement(IntentionAgreement intentionAgreement);

    @Select("select count(*) from intention_agreement where form_id = #{formId}")
    int isFormExist(Long formId);

    @Update("update intention_agreement set status = #{status} where form_id = #{formId}")
    int updateIntentionAgreementStatus(Long formId,String status);

    @Update("update intention_agreement set approval_status = #{approvalStatus} where form_id = #{formId}")
    int updateIntentionAgreementApprovalStatus(Long formId, String approvalStatus);

    @Select("select * from intention_agreement where status = '已发' and approval_status = '审批完成' and reset_flag = 0")
    List<IntentionAgreement> getApprovingIntentionAgreement();

    @Update("update intention_agreement set reset_flag = #{resetFlag} where  form_id = #{formId}")
    int updateIntentionAgreementResetFlag(Long formId,Integer resetFlag);

    @Select("select a.*, b.enterprise_name from intention_agreement a inner join enterprise b where a.enterprise_id = b.enterprise_id and form_id = #{formId}")
    IntentionAgreement getIntentionAgreementByFormId(Long formId);

    @Select("select distinct a.form_id, a.enterprise_id, b.enterprise_name, b.contact, b.contact_tel, a.apply_date, a.deadline," +
            " a.approval_status from intention_agreement a inner join enterprise b where a.enterprise_id = b.enterprise_id" +
            " and a.status = #{status} and " +
            "((a.form_id like '%${value}%') or (a.enterprise_id like '%${value}%') or (b.enterprise_name like '%${value}%')" +
            " or (a.earnest like '%${value}%') or (a.deadline like '%${value}%') or (a.apply_date like '%${value}%') or (a.applicant like '%${value}%')" +
            " or (a.remark like '%${value}%') or (a.form_name like '%${value}%') or (a.approval_status like '%${value}%'))" +
            " order by a.form_id desc")
    List<IntentionAgreement> searchIntentionAgreementByKey(String value,String status);

    @Select("select  count(distinct form_id) from intention_agreement a inner join enterprise b where a.enterprise_id = b.enterprise_id" +
            " and a.status = #{status} and " +
            "((a.form_id like '%${value}%') or (a.enterprise_id like '%${value}%') or (b.enterprise_name like '%${value}%')" +
            " or (a.earnest like '%${value}%') or (a.deadline like '%${value}%') or (a.apply_date like '%${value}%') or (a.applicant like '%${value}%')" +
            " or (a.remark like '%${value}%') or (a.form_name like '%${value}%') or (a.approval_status like '%${value}%'))")
    int getSizeOfSearchIntentionAgreementByKey(String value,String status);
}
