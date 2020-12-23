package com.rzh.iot.dao;

import com.rzh.iot.model.Enterprise;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EnterpriseDao {

    @Select("select a.enterprise_id, a.enterprise_name , a.industry_type_id , a.enter_park, a.enter_time , a.status , b.industry_type_name , c.space_name" +
            " from enterprise a inner join industry_type b , space_data c " +
            " where a.industry_type_id = b.industry_type_id and a.enter_park = c.space_id order by a.enterprise_id desc limit #{currentPage} , #{limit}")
    List<Enterprise> getEnterpriseList(Integer currentPage,Integer limit);

    @Select("select count(*)" +
            " from enterprise a inner join industry_type b , space_data c " +
            " where a.industry_type_id = b.industry_type_id and a.enter_park = c.space_id order by a.enterprise_id desc")
    int getCountOfEnterpriseList();

    @Select("select * from enterprise a inner join industry_type b , space_data c where a.industry_type_id = b.industry_type_id and a.enter_park = c.space_id and a.enterprise_id = #{enterpriseId}")
    Enterprise getEnterpriseByEnterpriseId(Long enterpriseId);

    @Select("select count(*) from enterprise where enterprise_id = #{enterpriseId}")
    int isEnterpriseExist(Long enterpriseId);

    @Select("select count(*) from enterprise where enterprise_name = #{enterpriseName}")
    int isEnterpriseNameExist(String enterpriseName);

    @Insert("insert into enterprise" +
            " (enterprise_name,status,enterprise_nature,enter_time,office_address,enterprise_legal_person," +
            "registration_time,contact,contact_tel,contact_department,contact_position,enterprise_email," +
            "registered_capital,industry_type_id,enterprise_area,business_registration_type,enterprise_introduction," +
            "enter_park,record_date,contract_type,enterprise_tel) " +
            "values" +
            " (#{enterpriseName},#{status},#{enterpriseNature},#{enterTime},#{officeAddress},#{enterpriseLegalPerson}," +
            "#{registrationTime},#{contact},#{contactTel},#{contactDepartment},#{contactPosition},#{enterpriseEmail}," +
            "#{registeredCapital},#{industryTypeId},#{enterpriseArea},#{businessRegistrationType},#{enterpriseIntroduction}," +
            "#{enterPark},#{recordDate},#{contractType},#{enterpriseTel})")
    int createEnterprise(Enterprise enterprise);

    @Update("update enterprise set " +
            "enterprise_name = #{enterpriseName} , status = #{status} , enterprise_nature = #{enterpriseNature} , " +
            "enter_time = #{enterTime} , office_address = #{officeAddress} , enterprise_legal_person = #{enterpriseLegalPerson}," +
            "registration_time = #{registrationTime} , contact = #{contact} , contact_tel = #{contactTel} , " +
            "contact_department = #{contactDepartment} , contact_position = #{contactPosition} , enterprise_email = #{enterpriseEmail} , " +
            "registered_capital = #{registeredCapital} , industry_type_id = #{industryTypeId} , enterprise_area = #{enterpriseArea} ," +
            "business_registration_type = #{businessRegistrationType} , enterprise_introduction = #{enterpriseIntroduction} , " +
            "enter_park = #{enterPark} , record_date = #{recordDate} , contract_type = #{contractType} , enterprise_tel = #{enterpriseTel}" +
            " where enterprise_id = #{enterpriseId}")
    int updateEnterprise(Enterprise enterprise);

    @Select("select distinct * from enterprise a inner join industry_type b , space_data c where" +
            " a.industry_type_id = b.industry_type_id and a.enter_park = c.space_id and " +
            "((a.enterprise_id like '%${value}%') or (a.enterprise_name like '%${value}%') or (a.status like '%${value}%') " +
            " or (a.enterprise_nature like '%${value}%') or (a.enter_time like '%${value}%') or (a.office_address like '%${value}%')" +
            " or (a.enterprise_legal_person like '%${value}%') or (a.registration_time like '%${value}%') or (a.contact like '%${value}%')" +
            " or (a.contact_tel like '%${value}%') or (a.contact_department like '%${value}%') or (a.contact_position like '%${value}%')" +
            " or (a.enterprise_email like '%${value}%') or (a.registered_capital like '%${value}%') or (a.industry_type_id like '%${value}%')" +
            " or (a.enterprise_area like '%${value}%') or (a.business_registration_type like '%${value}%') or (a.enterprise_introduction like '%${value}%')" +
            " or (a.enter_park like '%${value}%') or (a.record_date like '%${value}%') or (a.contract_type like '%${value}%')" +
            " or (a.enterprise_tel like '%${value}%') or (b.industry_type_name like '%${value}%') or (c.space_name like '%${value}%')) order by enterprise_id desc")
    List<Enterprise> getEnterpriseListByKey(String key);

    @Select("select distinct count(*) from enterprise a inner join industry_type b , space_data c where" +
            " a.industry_type_id = b.industry_type_id and a.enter_park = c.space_id and " +
            "((a.enterprise_id like '%${value}%') or (a.enterprise_name like '%${value}%') or (a.status like '%${value}%') " +
            " or (a.enterprise_nature like '%${value}%') or (a.enter_time like '%${value}%') or (a.office_address like '%${value}%')" +
            " or (a.enterprise_legal_person like '%${value}%') or (a.registration_time like '%${value}%') or (a.contact like '%${value}%')" +
            " or (a.contact_tel like '%${value}%') or (a.contact_department like '%${value}%') or (a.contact_position like '%${value}%')" +
            " or (a.enterprise_email like '%${value}%') or (a.registered_capital like '%${value}%') or (a.industry_type_id like '%${value}%')" +
            " or (a.enterprise_area like '%${value}%') or (a.business_registration_type like '%${value}%') or (a.enterprise_introduction like '%${value}%')" +
            " or (a.enter_park like '%${value}%') or (a.record_date like '%${value}%') or (a.contract_type like '%${value}%')" +
            " or (a.enterprise_tel like '%${value}%') or (b.industry_type_name like '%${value}%') or (c.space_name like '%${value}%'))")
    int getSizeOfEnterpriseListByKey(String key);
}
