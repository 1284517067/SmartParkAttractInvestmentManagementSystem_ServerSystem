package com.rzh.iot.dao;

import com.rzh.iot.model.LeaseContractRoom;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LeaseContractRoomDao {

    @Insert("<script>" +
            "insert into lease_contract_room " +
            "(space_id, form_id)" +
            " values " +
            "<foreach collection='rooms' item='item' index='index' separator=','>" +
            " (#{item.spaceId}, #{item.formId}) " +
            "</foreach>" +
            "</script>")
    int createLeaseContractRooms(@Param(value = "rooms") List<LeaseContractRoom> rooms);

    @Delete("delete from lease_contract_room where form_id = #{formId}")
    int deleteLeaseContractRoomsByFormId(Long formId);

    @Select("select * from lease_contract_room where form_id = #{formId}")
    List<LeaseContractRoom> getLeaseContractRoomByFormId(Long formId);
}
