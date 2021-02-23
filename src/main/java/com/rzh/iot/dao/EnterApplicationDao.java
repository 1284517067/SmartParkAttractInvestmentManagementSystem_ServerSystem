package com.rzh.iot.dao;

import com.rzh.iot.model.EnterApplication;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EnterApplicationDao {

    @Select("select a.form_id, b.enterprise_name, a.enter_time, a.principal, a.approval_status from enter_application a inner join enterprise b" +
            " where a.status = #{status} and a.enterprise_id = b.enterprise_id order by a.form_id desc limit #{currentPage} , #{limit}")
    List<EnterApplication> getEnterApplicationTableData(Integer limit, Integer currentPage, String status);

    @Select("select count(*) from enter_application where status = #{status}")
    int getSizeOfEnterApplicationTableData(String status);

    @Select("select a.*, b.enterprise_name from enter_application a inner join enterprise b where a.enterprise_id = b.enterprise_id and a.form_id = #{formId}")
    EnterApplication getEnterApplicationByFormId(Long formId);

    @Select("select count(*) from enter_application where form_id = #{formId}")
    int isEnterApplicationExist(Long formId);

    @Insert("insert into enter_application " +
            "(form_name, enterprise_id, contract_id, enter_time, enter_population, principal, enter_description, status, approval_status) " +
            "values " +
            "(#{formName}, #{enterpriseId}, #{contractId}, #{enterTime}, #{enterPopulation}, #{principal}, #{enterDescription}, #{status}, #{approvalStatus})")
    @Options(useGeneratedKeys = true, keyColumn = "form_id", keyProperty = "formId")
    int createEnterApplication(EnterApplication enterApplication);

    @Update("update enter_application set " +
            "form_name = #{formName}, enterprise_id = #{enterpriseId}, contract_id = #{contractId}, " +
            "enter_time = #{enterTime}, enter_population = #{enterPopulation}, principal = #{principal}, " +
            "enter_description = #{enterDescription}, status = #{status}, approval_status = #{approvalStatus} " +
            "where form_id = #{formId}")
    int updateEnterApplication(EnterApplication enterApplication);

    @Update("update enter_application set status = #{status} where form_id = #{formId}")
    int updateEnterApplicationStatusByFormId(Long formId, String status);

    @Update("update enter_application set approval_status = #{approvalStatus} where form_id = #{formId}")
    int updateEnterApplicationApprovalStatusByFormId(Long formId, String approvalStatus);

    @Select("select count(*) from enter_application where form_name = #{formName}")
    int isNameExist(String formName);
}
