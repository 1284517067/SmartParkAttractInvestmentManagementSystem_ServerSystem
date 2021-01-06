package com.rzh.iot.dao;

import com.rzh.iot.model.ApprovalOpinion;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApprovalOpinionDao {

    @Insert("insert into approval_opinion (opinion, form_id, position_id, approval_process_node_name, " +
            "handler, prev_node, status, contract_type) values (#{opinion}, #{formId}, #{positionId}, " +
            "#{approvalProcessNodeName}, #{handler}, #{prevNode}, #{status},#{contractType})")
    @Options(useGeneratedKeys = true,keyColumn = "opinion_id",keyProperty = "opinionId")
    int createApprovalOpinion(ApprovalOpinion approvalOpinion);

    @Update("update approval_opinion set opinion = #{opinion}, form_id = #{formId}, " +
            "position_id = #{positionId}, approval_process_name = #{approvalProcessName}, " +
            "handler = #{handler}, prev_node = #{prevNode}, status = #{status}, contract_type = #{contractType}" +
            " where opinion_id = #{opinionId}")
    int updateApprovalOpinion(ApprovalOpinion approvalOpinion);

    @Update("update approval_opinion set opinion = #{opinion}, status = #{status} where opinion_id = #{opinionId}")
    int updateOpinion(Long opinionId,String opinion,String status);

    @Select("select count(*) from approval_opinion where opinion_id = #{opinionId}")
    int isOpinionExist(Long opinionId);

    @Select("select * from approval_opinion where form_id = #{formId} and contract_type = #{contractType}")
    List<ApprovalOpinion> getStepByFormId(Long formId,String contractType);

    @Select("select opinion_id, position_id, approval_process_node_name from approval_opinion where status = '待审批' and form_id = #{formId} and contract_type = #{contractType} order by opinion_id limit 1")
    ApprovalOpinion getFirstWaitingApprovalOpinion(Long formId,String contractType);

}
