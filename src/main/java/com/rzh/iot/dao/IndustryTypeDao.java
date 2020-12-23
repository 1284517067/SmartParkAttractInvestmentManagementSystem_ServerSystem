package com.rzh.iot.dao;

import com.rzh.iot.model.IndustryType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IndustryTypeDao {
    @Select("select * from industry_type where status = '正常' order by industry_type_id desc")
    List<IndustryType> getIndustryType();

    @Select("select * from industry_type where status = '正常' order by industry_type_id desc limit #{currentPage} ,#{limit};")
    List<IndustryType> getIndustryTypeByLimit(Integer currentPage,Integer limit);

    @Select("select industry_type_name from industry_type where industry_type_id = #{fatherNodeId} and status = '正常'")
    String getParentNameById(Long fatherNodeId);

    @Insert("insert into industry_type (industry_type_name,father_node_id,remark,status) values (#{industryTypeName},#{fatherNodeId},#{remark},#{status}) ")
    int createIndustryType(IndustryType industryType);

    @Update("update industry_type set industry_type_name = #{industryTypeName} , father_node_id = #{fatherNodeId} , remark = #{remark} , status = #{status} where industry_type_id = #{industryTypeId}")
    int updateIndustryType(IndustryType industryType);

    @Update("update industry_type set status = '废弃' where industry_type_id = #{industryTypeId}")
    int deleteIndustryTypeById(Long industryTypeId);

    @Select("select * from industry_type where status = '正常' and ((industry_type_id like '%${value}%') or (industry_type_name like '%${value}%') or (father_node_id like '%${value}%') or (remark like '%${value}%')) order by industry_type_id desc")
    List<IndustryType> getIndustryTypeDataByKey(String key);

    @Select("select count(*) from industry_type where status = '正常'")
    Integer getTotalCount();

    @Select("select count(*) from industry_type where industry_type_name = #{industryTypeName}")
    int isNameInvoke(IndustryType industryType);

    @Select("select industry_type_id , industry_type_name from industry_type where status = '正常'")
    List<IndustryType> getIndustryTypeList();
}
