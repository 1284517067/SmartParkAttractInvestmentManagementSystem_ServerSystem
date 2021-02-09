package com.rzh.iot.dao;

import com.rzh.iot.model.IntentionAgreementRoom;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IntentionAgreementRoomDao {

    @Select("select * from intention_agreement_room where form_id = #{formId}")
    List<IntentionAgreementRoom> getIntentionAgreementRoomsByFormId(Long formId);

    @Insert("<script>" +
            "insert into intention_agreement_room (form_id,space_id) values " +
            "<foreach collection='rooms' item='item' index='index' separator=','>" +
            "(#{item.formId}, #{item.spaceId})" +
            "</foreach>" +
            "</script>")
    int createIntentionAgreementRooms(@Param(value = "rooms") List<IntentionAgreementRoom> rooms);


    @Delete("delete from intention_agreement_room where form_id = #{formId}")
    int deleteIntentionAgreementRooms(Long formId);
}
