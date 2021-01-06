package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ApprovalOpinion implements Serializable {
    private Long opinionId;

    private String approvalProcessNodeName;

    private String opinion;

    private Long formId;

    private Long positionId;

    private String positionName;

    private String departmentName;

    private String handler;

    private Long prevNode;

    private String status;

    private String approvalDate;

    private String contractType;

    public ApprovalOpinion() {
    }

    @Override
    public String toString() {
        return "ApprovalOpinion{" +
                "opinionId=" + opinionId +
                ", approvalProcessNodeName='" + approvalProcessNodeName + '\'' +
                ", opinion='" + opinion + '\'' +
                ", formId=" + formId +
                ", positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", handler='" + handler + '\'' +
                ", prevNode=" + prevNode +
                ", status='" + status + '\'' +
                ", approvalDate='" + approvalDate + '\'' +
                ", contractType='" + contractType + '\'' +
                '}';
    }

    public Long getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Long opinionId) {
        this.opinionId = opinionId;
    }

    public String getApprovalProcessNodeName() {
        return approvalProcessNodeName;
    }

    public void setApprovalProcessNodeName(String approvalProcessNodeName) {
        this.approvalProcessNodeName = approvalProcessNodeName;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
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

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Long getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Long prevNode) {
        this.prevNode = prevNode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
}
