package com.rzh.iot.dao;

import com.rzh.iot.model.Space;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SpaceDao {

    @Select("select * from space_data where space_type = '园区'")
    List<Space> getSpaceList();

    @Select("select * from space_data where parent_node_id = #{spaceId}")
    List<Space> getSpaceListByParentNodeId(Long spaceId);

    @Select("select space_name from space_data where space_id = #{spaceId}")
    String getSpaceNameBySpaceId(Long spaceId);

    @Insert("insert into space_data (space_name,space_address,area_covered,floor_space,space_type,price,parent_node_id,lease_area,status) values (#{spaceName},#{spaceAddress},#{areaCovered},#{floorSpace},#{spaceType},#{price},#{parentNodeId},#{leaseArea},#{status})")
    int createSpace(Space space);

    @Update("update space_data set space_name = #{spaceName} , space_address = #{spaceAddress} , area_covered = #{areaCovered} , floor_space = #{floorSpace} , space_type = #{spaceType} , price = #{price} , parent_node_id = #{parentNodeId} , lease_area = #{leaseArea} , status = #{status} where space_id = #{spaceId}")
    int updateSpace(Space space);

    @Select("select count(*) from space_data where space_id = #{spaceId}")
    int isSpaceIdExist(Long spaceId);

    @Select("select count(*) from space_data where space_name = #{spaceName} and parent_node_id = #{parentNodeId}")
    int isSpaceExist(Space space);

    @Delete("delete from space_data where space_id = #{spaceId}")
    int deleteSpace(Long spaceId);

    @Select("select space_id,space_name from space_data where parent_node_id is null")
    List<Space> getParkList();

}
