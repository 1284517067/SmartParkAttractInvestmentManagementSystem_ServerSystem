package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class LeaseContract implements Serializable {

    private Long formId;

    private String formName;

    private Long enterpriseId;

    private String enterpriseName;

    private String startDate;

    private String expiryDate;

    private Integer resetFlag;

    private Long deposit;

    private Integer warningDate;

    private List<LeaseContractRoom> spaces;

    private String signDate;

    private String applyDate;

    private String applicant;

    private String status;

    private String approvalStatus;

    private List<LeaseContractPayItem> payItems;

    public LeaseContract() {
    }

    @Override
    public String toString() {
        return "LeaseContract{" +
                "formId=" + formId +
                ", formName='" + formName + '\'' +
                ", enterpriseId=" + enterpriseId +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", resetFlag=" + resetFlag +
                ", deposit=" + deposit +
                ", warningDate=" + warningDate +
                ", spaces=" + spaces +
                ", signDate='" + signDate + '\'' +
                ", applyDate='" + applyDate + '\'' +
                ", applicant='" + applicant + '\'' +
                ", status='" + status + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", payItems=" + payItems +
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Integer getWarningDate() {
        return warningDate;
    }

    public void setWarningDate(Integer warningDate) {
        this.warningDate = warningDate;
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

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplicant() {
        return applicant;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public List<LeaseContractRoom> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<LeaseContractRoom> spaces) {
        this.spaces = spaces;
    }

    public List<LeaseContractPayItem> getPayItems() {
        return payItems;
    }

    public void setPayItems(List<LeaseContractPayItem> payItems) {
        this.payItems = payItems;
    }

    public Integer getResetFlag() {
        return resetFlag;
    }

    public void setResetFlag(Integer resetFlag) {
        this.resetFlag = resetFlag;
    }
}
