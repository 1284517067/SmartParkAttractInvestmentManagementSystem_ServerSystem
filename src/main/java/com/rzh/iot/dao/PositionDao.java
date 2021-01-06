package com.rzh.iot.dao;

import com.rzh.iot.model.Position;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PositionDao {

    @Select("select position_name from position where position_id = #{position_id}")
    String getPositionNameById(Long positionId);

    @Select("select position_id , position_name from position where department_id = #{departmentId}")
    List<Position> getPositionByDepartmentId(Long departmentId);

    @Select("select * from position order by position_id desc limit #{currentPage} , #{limit}")
    List<Position> getPositionList(Integer currentPage,Integer limit);

    @Update("update position set position_name = #{positionName} , department_id = #{departmentId} where position_id = #{positionId}")
    int updatePosition(Position position);

    @Delete("delete from position where position_id = #{positionId}")
    int deletePositionByPositionId(Long positionId);

    @Select("select count(*) from position")
    Integer getPositionTotal();

    @Insert("insert into position (position_name,department_id) values (#{positionName},#{departmentId})")
    int createPosition(Position position);

    @Select("select count(*) from position where position_id = #{positionId}")
    int isExist(Position position);

    @Select("select count(*) from position where position_name = #{positionName} and department_id = #{departmentId}")
    int isNameDuplication(Position position);

    @Select("select * from position a inner join department b where a.department_id = b.department_id and ((a.position_id like '%${value}%') or (a.position_name like '%${value}%') or (a.department_id like '%${value}%') or (b.department_name like '%${value}%'))")
    List<Position> getPositionListByKey(String key);

    @Select("select count(*) from position a inner join department b where a.department_id = b.department_id and ((a.position_id like '%${value}%') or (a.position_name like '%${value}%') or (a.department_id like '%${value}%') or (b.department_name like '%${value}%'))")
    int getPositionCountByKey(String key);

    @Select("select position_id from position where department_id = #{departmentId} and position_name = #{positionName}")
    Long getPositionIdByDepartmentIdAndPositionName(Long departmentId,String positionName);
}
