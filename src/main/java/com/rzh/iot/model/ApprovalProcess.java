package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class ApprovalProcess implements Serializable {
    private Long approvalProcessId;

    private String approvalProcessName;

    private String contractType;

    private String processDescription;

    private String businessType;

    private String status;

    private List<ApprovalProcessNode> nodes;

    public List<ApprovalProcessNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ApprovalProcessNode> nodes) {
        this.nodes = nodes;
    }

    public ApprovalProcess() {
    }

    public Long getApprovalProcessId() {
        return approvalProcessId;
    }

    public void setApprovalProcessId(Long approvalProcessId) {
        this.approvalProcessId = approvalProcessId;
    }

    public String getApprovalProcessName() {
        return approvalProcessName;
    }

    public void setApprovalProcessName(String approvalProcessName) {
        this.approvalProcessName = approvalProcessName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getProcessDescription() {
        return processDescription;
    }

    public void setProcessDescription(String processDescription) {
        this.processDescription = processDescription;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApprovalProcess{" +
                "approvalProcessId=" + approvalProcessId +
                ", approvalProcessName='" + approvalProcessName + '\'' +
                ", contractType='" + contractType + '\'' +
                ", processDescription='" + processDescription + '\'' +
                ", businessType='" + businessType + '\'' +
                ", status='" + status + '\'' +
                ", nodes=" + nodes +
                '}';
    }
}
