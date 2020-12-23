package com.rzh.iot.dao;

import com.rzh.iot.model.ApprovalProcess;
import com.rzh.iot.model.ApprovalProcessNode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApprovalProcessNodeDao {

    @Select("select approval_process_node_id , approval_process_node_name , prev_node_id , position_id from approval_process_node where approval_process_id = #{approval_process_id}")
    List<ApprovalProcessNode> getApprovalProcessNodeListByApprovalProcessId(Long approvalProcessId);

    @Insert("insert into approval_process_node (approval_process_node_name, prev_node_id, position_id,approval_process_id) values (#{approvalProcessNodeName},#{prevNodeId},#{positionId},#{approvalProcessId})")
    @Options(useGeneratedKeys = true,keyProperty = "approvalProcessNodeId",keyColumn = "approval_process_node_id")
    void createApprovalProcessNode(ApprovalProcessNode node);

    @Select("select count(*) from approval_process_node where approval_process_id = #{approvalProcessId}")
    int isApprovalProcessExist(Long approvalProcessId);

    @Select("select * from approval_process_node")
    List<ApprovalProcessNode> getApprovalProcessNodeList();

    @Select("select a.approval_process_id , b.approval_process_name from approval_process_node a inner join approval_process b where a.approval_process_id = b.approval_process_id group by a.approval_process_id order by a.approval_process_id desc limit #{currentPage} , #{limit}")
    List<ApprovalProcess> getApprovalProcessList(Integer currentPage,Integer limit);

    @Select("select count(distinct approval_process_id) from approval_process_node")
    int getApprovalProcessCount();

    @Delete("delete from approval_process_node where approval_process_id = #{approvalProcessId}")
    int deleteApprovalProcessNodeByApprovalProcessId(Long approvalProcessId);

    @Select("select distinct a.approval_process_id,b.approval_process_name from approval_process_node a " +
            " inner join approval_process b " +
            " where a.approval_process_id = b.approval_process_id" +
            " and ((a.approval_process_node_id like '%${value}%')" +
            " or (a.approval_process_node_name like '%${value}%')" +
            " or (a.prev_node_id like '%${value}%')" +
            " or (a.position_id like '%${value}%')" +
            " or (a.approval_process_id like '%${value}%'))")
    List<ApprovalProcess> getApprovalProcessListByKey(String key);

    @Select("select count(distinct a.approval_process_id) from approval_process_node a " +
            " inner join approval_process b " +
            " where a.approval_process_id = b.approval_process_id" +
            " and ((a.approval_process_node_id like '%${value}%')" +
            " or (a.approval_process_node_name like '%${value}%')" +
            " or (a.prev_node_id like '%${value}%')" +
            " or (a.position_id like '%${value}%')" +
            " or (a.approval_process_id like '%${value}%'))")
    int getCountOfApprovalProcessByKey(String key);
}

