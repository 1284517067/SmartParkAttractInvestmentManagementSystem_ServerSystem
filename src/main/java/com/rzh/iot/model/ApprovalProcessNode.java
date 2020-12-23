package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ApprovalProcessNode implements Serializable {

    private Long approvalProcessNodeId;

    private String approvalProcessNodeName;

    private Long prevNodeId;

    private Long positionId;

    private Long approvalProcessId;

    private String positionName;

    private String departmentName;

    @Override
    public String toString() {
        return "ApprovalProcessNode{" +
                "approvalProcessNodeId=" + approvalProcessNodeId +
                ", approvalProcessNodeName='" + approvalProcessNodeName + '\'' +
                ", fatherNodeId=" + prevNodeId +
                ", positionId=" + positionId +
                ", approvalProcessId=" + approvalProcessId +
                ", positionName='" + positionName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

    public Long getApprovalProcessNodeId() {
        return approvalProcessNodeId;
    }

    public void setApprovalProcessNodeId(Long approvalProcessNodeId) {
        this.approvalProcessNodeId = approvalProcessNodeId;
    }

    public String getApprovalProcessNodeName() {
        return approvalProcessNodeName;
    }

    public void setApprovalProcessNodeName(String approvalProcessNodeName) {
        this.approvalProcessNodeName = approvalProcessNodeName;
    }

    public Long getPrevNodeId() {
        return prevNodeId;
    }

    public void setPrevNodeId(Long prevNodeId) {
        this.prevNodeId = prevNodeId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getApprovalProcessId() {
        return approvalProcessId;
    }

    public void setApprovalProcessId(Long approvalProcessId) {
        this.approvalProcessId = approvalProcessId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public ApprovalProcessNode() {
    }
}
