package com.rzh.iot.dao;

import com.rzh.iot.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MessageDao {

    @Insert("insert into message (username, department_id, position_id, form_id, message, applicant, type, status, create_date, contract_type)" +
            " values " +
            "(#{username}, #{departmentId}, #{positionId}, #{formId}, #{message}, #{applicant}, #{type}, #{status}, #{createDate},#{contractType})")
    int createMessage(Message message);

    @Update("update message set username = #{username}, department_id = #{departmentId}, position_id = #{positionId}, " +
            "form_id = #{formId}, message = #{message}, applicant = #{applicant}, type = #{type}, status = #{status}, " +
            "create_date = #{createDate} , contract_type = #{contractType}, principal = #{principal} where message_id = #{messageId}")
    int updateMessage(Message message);

    @Select("select count(*) from message where message_id = #{messageId}")
    int isMessageExist(Long messageId);

    @Select("select message_id, form_id, applicant, message, create_date, contract_type from message where principal = #{principal} and type = #{type} order by message_id desc")
    List<Message> getMessage(String type, String principal);

    @Select("select message_id, form_id, applicant, message, create_date, contract_type from message" +
            " where type = #{type} and (username = #{username} or position_id = #{positionId} or department_id = #{departmentId} ) order by message_id desc")
    List<Message> getMessages(String username, Long departmentId, Long positionId, String type);

    @Update("update message set principal = #{principal} where message_id = #{messageId}")
    int updatePrincipalByMessageId(Long messageId,String principal);

    @Select("select count(*) from message where type = #{type} and (username = #{username} or position_id = #{positionId} or department_id = #{departmentId} )")
    int getCountOfMessage(String username, Long departmentId, Long positionId, String type);

    @Select("select count(*) from message where principal = #{principal} and type = #{type}")
    int getCountOfMessages(String type, String principal);

    @Update("update message set type = #{type}, principal = #{principal} where message_id = #{messageId}")
    int updateMessageType(Long messageId,String type, String principal);

}
