package com.rzh.iot.dao;

import com.rzh.iot.model.IntentionRegistrationForm;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IntentionRegistrationFormDao {

    @Select("select a.form_id, a.apply_date, a.enterprise_name, a.enterprise_area, a.registered_capital, a.source, " +
            "a.contact, a.contact_tel, a.approval_status, b.industry_type_name from intention_registration_form a inner join " +
            "industry_type b where a.industry_type_id = b.industry_type_id and a.status = #{status} order by form_id desc limit #{currentPage}, #{limit}")
    List<IntentionRegistrationForm> getIntentionRegistrationFormTableData(Integer currentPage,Integer limit,String status);

    @Select("select count(*) from intention_registration_form where status = #{status}")
    int getSizeOfIntentionRegistrationFormTableData(String status);

    @Insert("insert into intention_registration_form (form_name, enterprise_name, enterprise_area, industry_type_id, " +
           "source, contact, contact_tel, contact_department, contact_position, qq, contact_email, enterprise_tel, " +
           "enterprise_url, enterprise_legal_person, registration_time, registered_capital, department_id, department_manager, " +
           "apply_date, principal, contract_type, status, business_registration_type, approval_status) values " +
           "(#{formName}, #{enterpriseName}, #{enterpriseArea}, #{industryTypeId}, #{source}, #{contact}, #{contactTel}, " +
           "#{contactDepartment}, #{contactPosition}, #{qq}, #{contactEmail}, #{enterpriseTel}, #{enterpriseUrl}, " +
           "#{enterpriseLegalPerson}, #{registrationTime}, #{registeredCapital}, #{departmentId}, #{departmentManager}, " +
           "#{applyDate}, #{principal}, #{contractType}, #{status}, #{businessRegistrationType}, #{approvalStatus})")
    @Options(useGeneratedKeys = true,keyProperty = "formId",keyColumn = "form_id")
    int createIntentionRegistrationForm(IntentionRegistrationForm form);

    @Update("update intention_registration_form set form_name = #{formName}, enterprise_name = #{enterpriseName}, enterprise_area = #{enterpriseArea}," +
           " industry_type_id = #{industryTypeId}, source = #{source}, contact = #{contact}, contact_tel = #{contactTel}," +
           " contact_email = #{contactEmail}, contact_department = #{contactDepartment}, contact_position = #{contactPosition}, qq = #{qq}," +
           " enterprise_tel = #{enterpriseTel}, apply_date = #{applyDate}, principal = #{principal}, contract_type = #{contractType}," +
           " status = #{status}, business_registration_type = #{businessRegistrationType}, approval_status = #{approvalStatus}" +
           " where form_id = #{formId}")
    int updateIntentionRegistrationForm(IntentionRegistrationForm form);

    @Select("select count(*) from intention_registration_form where form_id = #{formId}")
    int isFormExist(Long formId);

    @Select("select * from intention_registration_form where form_id = #{formId}")
    IntentionRegistrationForm getIntentionRegistrationFormByFormId(Long formId);

    @Update("update intention_registration_form set status = #{status} where form_id = #{formId}")
    int updateStatusByFormId(Long formId,String status);

    @Select("select distinct a.form_id, a.apply_date, a.enterprise_name, a.enterprise_area, a.registered_capital, a.source, " +
            "a.contact, a.contact_tel, a.approval_status, b.industry_type_name from intention_registration_form a inner join " +
            "industry_type b, department c where a.industry_type_id = b.industry_type_id and a.department_id = c.department_id and a.status = #{status} and" +
            "((a.form_id like '%${value}%') or (a.form_name like '%${value}%') or (a.enterprise_name like '%${value}%')" +
            " or (a.enterprise_area like '%${value}%') or (a.industry_type_id like '%${value}%') or (a.source like '%${value}%')" +
            " or (a.contact like '%${value}%') or (a.contact_tel like '%${value}%') or (a.contact_department like '%${value}%')" +
            " or (a.contact_position like '%${value}%') or (a.qq like '%${value}%') or (a.contact_email like '%${value}%')" +
            " or (a.enterprise_tel like '%${value}%') or (a.enterprise_url like '%${value}%') or (a.enterprise_legal_person like '%${value}%')" +
            " or (a.registration_time like '%${value}%') or (a.registered_capital like '%${value}%') or (a.department_id like '%${value}%')" +
            " or (a.department_manager like '%${value}%') or (a.apply_date like '%${value}%') or (a.principal like '%${value}%')" +
            " or (a.contract_type like '%${value}%') or (a.business_registration_type like '%${value}%')" +
            " or (a.approval_status like '%${value}%') or (b.industry_type_name like '%${value}%') or (c.department_name like '%${value}%'))" +
            " order by form_id desc")
    List<IntentionRegistrationForm> getIntentionRegistrationFormsByKey(String value,String status);

    @Select("select count(distinct form_id) from intention_registration_form a inner join " +
            "industry_type b, department c where a.industry_type_id = b.industry_type_id and a.department_id = c.department_id and a.status = #{status} and" +
            "((a.form_id like '%${value}%') or (a.form_name like '%${value}%') or (a.enterprise_name like '%${value}%')" +
            " or (a.enterprise_area like '%${value}%') or (a.industry_type_id like '%${value}%') or (a.source like '%${value}%')" +
            " or (a.contact like '%${value}%') or (a.contact_tel like '%${value}%') or (a.contact_department like '%${value}%')" +
            " or (a.contact_position like '%${value}%') or (a.qq like '%${value}%') or (a.contact_email like '%${value}%')" +
            " or (a.enterprise_tel like '%${value}%') or (a.enterprise_url like '%${value}%') or (a.enterprise_legal_person like '%${value}%')" +
            " or (a.registration_time like '%${value}%') or (a.registered_capital like '%${value}%') or (a.department_id like '%${value}%')" +
            " or (a.department_manager like '%${value}%') or (a.apply_date like '%${value}%') or (a.principal like '%${value}%')" +
            " or (a.contract_type like '%${value}%') or (a.business_registration_type like '%${value}%')" +
            " or (a.approval_status like '%${value}%') or (b.industry_type_name like '%${value}%') or (c.department_name like '%${value}%'))")
    int getSizeOfKey(String value,String status);

    @Update("update intention_registration_form set approval_status = #{approvalStatus} where form_id = #{formId}")
    int updateApprovalStatus(Long formId,String approvalStatus);
}