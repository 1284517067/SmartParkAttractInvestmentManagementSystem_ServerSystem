package com.rzh.iot.dao;

import com.rzh.iot.model.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentDao {

    @Select("select department_name from department where department_id = #{department_id}")
    String getDepartmentNameById(Long departmentId);

    @Select("select department_name from department where department_id = (select department_id from position where position_id = #{position_id})")
    String getDepartmentNameByPositionId(Long positionId);

    @Select("select department_id , department_name from department")
    List<Department> getDepartmentListData();

    @Select("select * from department order by department_id desc limit #{currentPage} , #{limit}")
    List<Department> getDepartmentListByLimit(Integer currentPage,Integer limit);

    @Select("select count(*) from department where department_name = #{departmentName}")
    int isNameDuplication(Department department);

    @Insert("insert into department (department_name) values (#{departmentName})")
    int createDepartment(Department department);

    @Update("update department set department_name = #{departmentName} where department_id = #{departmentId}")
    int updateDepartment(Department department);

    @Select("select count(*) from department where department_id = #{departmentId}")
    int isExist(Department department);

    @Select("select count(*) from department")
    int getDepartmentTotal();

    @Delete("delete from department where department_id = #{departmentId}")
    int deleteDepartmentByDepartmentId(Long departmentId);

    @Select("select * from department where (department_id like '%${value}%') or (department_name like '%${value}%')")
    List<Department> getDepartmentListByKey(String key);

    @Select("select count(*) from department where (department_id like '%${value}%') or (department_name like '%${value}%')")
    int getDepartmentCountByKey(String key);

    @Select("select department_id from department where department_id = (select department_id from position where position_id = #{position_id})")
    Long getDepartmentIdByPositionId(Long positionId);
}
