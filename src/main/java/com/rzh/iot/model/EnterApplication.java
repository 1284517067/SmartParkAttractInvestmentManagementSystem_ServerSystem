package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class EnterApplication implements Serializable {

    private Long formId;

    private String formName;

    private Long enterpriseId;

    private String enterpriseName;

    private Long contractId;

    private String contractName;

    private String enterTime;

    private Long enterPopulation;

    private String principal;

    private String enterDescription;

    private String status;

    private String approvalStatus;

    public EnterApplication() {
    }

    @Override
    public String toString() {
        return "EnterApplication{" +
                "formId=" + formId +
                ", formName='" + formName + '\'' +
                ", enterpriseId=" + enterpriseId +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", contractId=" + contractId +
                ", contractName='" + contractName + '\'' +
                ", enterTime='" + enterTime + '\'' +
                ", enterPopulation=" + enterPopulation +
                ", principal='" + principal + '\'' +
                ", enterDescription='" + enterDescription + '\'' +
                ", status='" + status + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                '}';
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public Long getEnterPopulation() {
        return enterPopulation;
    }

    public void setEnterPopulation(Long enterPopulation) {
        this.enterPopulation = enterPopulation;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getEnterDescription() {
        return enterDescription;
    }

    public void setEnterDescription(String enterDescription) {
        this.enterDescription = enterDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}

