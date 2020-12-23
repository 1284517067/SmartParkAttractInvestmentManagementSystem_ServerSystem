package com.rzh.iot.dao;

import com.rzh.iot.model.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectDao {
    @Select("select a.project_id, a.enterprise_name, a.enterprise_area, a.industry_type_id, a.registered_capital, a.contact," +
            " a.contact_tel, a.source, a.principal , b.industry_type_name from project a inner join industry_type b where" +
            " a.industry_type_id = b.industry_type_id and a.status != '已删除' order by a.project_id desc limit #{currentPage}, #{limit}")
    List<Project> getProjectListByLimit(Integer currentPage,Integer limit);

    @Select("select count(*) from project where status != '已删除'")
    int getSizeOfProjectList();

    @Select("select a.project_id , a.enterprise_name , a.enterprise_area , a.industry_type_id , a.contact , a.contact_tel ," +
            " a.contact_department , a.contact_position , a.qq , a.contact_email , a.enterprise_tel , a.enterprise_url , " +
            " a.enterprise_legal_person , a.registration_time , a.registered_capital , a.department_id , a.principal , a.source , " +
            " a.status , b.industry_type_name , c.department_name from project a inner join industry_type b , department c where " +
            " a.industry_type_id = b.industry_type_id and a.department_id = c.department_id and project_id = #{projectId}")
    Project getProjectByProjectId(Long projectId);

    @Select("select count(*) from project where project_id = #{projectId}")
    int isProjectExist(Long projectId);

    @Select("select count(*) from project where enterprise_name = #{enterpriseName}")
    int isEnterpriseNameExist(String enterpriseName);

    @Insert("insert into project (enterprise_name,enterprise_area,industry_type_id,contact,contact_tel,contact_department,contact_position," +
            "qq,contact_email,enterprise_tel,enterprise_url,enterprise_legal_person,registration_time,registered_capital,department_id,principal," +
            "source,status) values (#{enterpriseName},#{enterpriseArea},#{industryTypeId},#{contact},#{contactTel},#{contactDepartment},#{contactPosition}," +
            "#{qq},#{contactEmail},#{enterpriseTel},#{enterpriseUrl},#{enterpriseLegalPerson},#{registrationTime},#{registeredCapital},#{departmentId},#{principal}," +
            "#{source},#{status})")
    int createProject(Project project);

    @Update("update project set enterprise_name = #{enterpriseName} , enterprise_area = #{enterpriseArea} , industry_type_id = #{industryTypeId} , " +
            " contact = #{contact} , contact_tel = #{contactTel} , contact_department = #{contactDepartment} , contact_position = #{contactPosition} , " +
            " qq = #{qq} , contact_email = #{contactEmail} , enterprise_tel = #{enterpriseTel} , enterprise_url = #{enterpriseUrl} , enterprise_legal_person = #{enterpriseLegalPerson} , " +
            " registration_time = #{registrationTime} , registered_capital = #{registeredCapital} , department_id = #{departmentId} , principal = #{principal} , " +
            " source = #{source} , status = #{status} " +
            " where project_id = #{projectId}")
    int updateUpdate(Project project);

    @Update("update project set status = '已删除' where project_id = #{projectId}")
    int deleteProject(Long projectId);

    @Update("update project set principal = #{username} where project_id = #{projectId}")
    int handleProject(Long projectId,String username);

    @Select("select a.project_id, a.enterprise_name, a.enterprise_area, a.industry_type_id, a.registered_capital, a.contact," +
            " a.contact_tel, a.source, a.principal , b.industry_type_name from project a inner join industry_type b , department c where" +
            " a.industry_type_id = b.industry_type_id and a.department_id = c.department_id and a.status != '已删除' and " +
            "((a.project_id like '%${value}%') or (a.enterprise_name like '%${value}%') or (a.enterprise_area like '%${value}%')" +
            " or (a.industry_type_id like '%${value}%') or (a.contact like '%${value}%') or (a.contact_tel like '%${value}%')" +
            " or (a.contact_department like '%${value}%') or (a.contact_position like '%${value}%') or (a.qq like '%${value}%')" +
            " or (a.contact_email like '%${value}%') or (a.enterprise_tel like '%${value}%') or (a.enterprise_url like '%${value}%')" +
            " or (a.enterprise_legal_person like '%${value}%') or (a.registration_time like '%${value}%') or (a.registered_capital like '%${value}%')" +
            " or (a.department_id like '%${value}%') or (a.principal like '%${value}%') or (b.industry_type_name like '%${value}%')" +
            " or (c.department_name like '%${value}%'))" +
            " order by a.project_id desc")
    List<Project> getProjectListByKey(String key);

    @Select("select count(project_id) from project a inner join industry_type b , department c where" +
            " a.industry_type_id = b.industry_type_id and a.department_id = c.department_id and a.status != '已删除' and " +
            "((a.project_id like '%${value}%') or (a.enterprise_name like '%${value}%') or (a.enterprise_area like '%${value}%')" +
            " or (a.industry_type_id like '%${value}%') or (a.contact like '%${value}%') or (a.contact_tel like '%${value}%')" +
            " or (a.contact_department like '%${value}%') or (a.contact_position like '%${value}%') or (a.qq like '%${value}%')" +
            " or (a.contact_email like '%${value}%') or (a.enterprise_tel like '%${value}%') or (a.enterprise_url like '%${value}%')" +
            " or (a.enterprise_legal_person like '%${value}%') or (a.registration_time like '%${value}%') or (a.registered_capital like '%${value}%')" +
            " or (a.department_id like '%${value}%') or (a.principal like '%${value}%') or (b.industry_type_name like '%${value}%')" +
            " or (c.department_name like '%${value}%'))" +
            " order by a.project_id desc")
    int getSizeOfProjectListByKey(String key);

    @Select("select a.project_id, a.enterprise_name, a.enterprise_area, a.industry_type_id, a.registered_capital, a.contact," +
            " a.contact_tel, a.source, a.principal , b.industry_type_name from project a inner join industry_type b where" +
            " a.industry_type_id = b.industry_type_id and a.status = '已删除' order by a.project_id desc limit #{currentPage}, #{limit}")
    List<Project> getDeletedProjectListByLimit(Integer currentPage,Integer limit);

    @Select("select count(*) from project where status = '已删除'")
    int getSizeOfDeletedProjectList();

    @Update("update project set status = '正常' where project_id = #{projectId}")
    int recoverProjectByProjectId(Long projectId);

    @Select("select a.project_id, a.enterprise_name, a.enterprise_area, a.industry_type_id, a.registered_capital, a.contact," +
            " a.contact_tel, a.source, a.principal , b.industry_type_name from project a inner join industry_type b , department c where" +
            " a.industry_type_id = b.industry_type_id and a.department_id = c.department_id and a.status = '已删除' and " +
            "((a.project_id like '%${value}%') or (a.enterprise_name like '%${value}%') or (a.enterprise_area like '%${value}%')" +
            " or (a.industry_type_id like '%${value}%') or (a.contact like '%${value}%') or (a.contact_tel like '%${value}%')" +
            " or (a.contact_department like '%${value}%') or (a.contact_position like '%${value}%') or (a.qq like '%${value}%')" +
            " or (a.contact_email like '%${value}%') or (a.enterprise_tel like '%${value}%') or (a.enterprise_url like '%${value}%')" +
            " or (a.enterprise_legal_person like '%${value}%') or (a.registration_time like '%${value}%') or (a.registered_capital like '%${value}%')" +
            " or (a.department_id like '%${value}%') or (a.principal like '%${value}%') or (b.industry_type_name like '%${value}%')" +
            " or (c.department_name like '%${value}%'))" +
            " order by a.project_id desc")
    List<Project> getDeletedProjectListByKey(String key);

    @Select("select count(project_id) from project a inner join industry_type b , department c where" +
            " a.industry_type_id = b.industry_type_id and a.department_id = c.department_id and a.status = '已删除' and " +
            "((a.project_id like '%${value}%') or (a.enterprise_name like '%${value}%') or (a.enterprise_area like '%${value}%')" +
            " or (a.industry_type_id like '%${value}%') or (a.contact like '%${value}%') or (a.contact_tel like '%${value}%')" +
            " or (a.contact_department like '%${value}%') or (a.contact_position like '%${value}%') or (a.qq like '%${value}%')" +
            " or (a.contact_email like '%${value}%') or (a.enterprise_tel like '%${value}%') or (a.enterprise_url like '%${value}%')" +
            " or (a.enterprise_legal_person like '%${value}%') or (a.registration_time like '%${value}%') or (a.registered_capital like '%${value}%')" +
            " or (a.department_id like '%${value}%') or (a.principal like '%${value}%') or (b.industry_type_name like '%${value}%')" +
            " or (c.department_name like '%${value}%'))" +
            " order by a.project_id desc")
    int getSizeOfDeletedProjectListByKey(String key);

}
