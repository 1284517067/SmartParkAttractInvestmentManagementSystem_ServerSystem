package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class IntentionAgreement implements Serializable {

     private Long formId;

     private String formName;

     private Long enterpriseId;

     private String enterpriseName;

     private Enterprise enterprise;

     private Long earnest;

     private String deadline;

     private String signDate;

     private String applicant;

     private List<Space> spaces;

     private String remark;

     private String status;

     private String approvalStatus;

     private String contact;

     private String contactTel;

     private Integer resetFlag;

     public IntentionAgreement() {
     }

     @Override
     public String toString() {
          return "IntentionAgreement{" +
                  "formId=" + formId +
                  ", formName='" + formName + '\'' +
                  ", enterpriseId=" + enterpriseId +
                  ", enterpriseName='" + enterpriseName + '\'' +
                  ", enterprise=" + enterprise +
                  ", earnest=" + earnest +
                  ", deadline='" + deadline + '\'' +
                  ", signDate='" + signDate + '\'' +
                  ", applicant='" + applicant + '\'' +
                  ", spaces=" + spaces +
                  ", remark='" + remark + '\'' +
                  ", status='" + status + '\'' +
                  ", approvalStatus='" + approvalStatus + '\'' +
                  ", contact='" + contact + '\'' +
                  ", contactTel='" + contactTel + '\'' +
                  ", resetFlag=" + resetFlag +
                  '}';
     }

     public String getContact() {
          return contact;
     }

     public void setContact(String contact) {
          this.contact = contact;
     }

     public String getContactTel() {
          return contactTel;
     }

     public void setContactTel(String contactTel) {
          this.contactTel = contactTel;
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

     public Enterprise getEnterprise() {
          return enterprise;
     }

     public void setEnterprise(Enterprise enterprise) {
          this.enterprise = enterprise;
     }

     public Long getEarnest() {
          return earnest;
     }

     public void setEarnest(Long earnest) {
          this.earnest = earnest;
     }

     public String getDeadline() {
          return deadline;
     }

     public void setDeadline(String deadline) {
          this.deadline = deadline;
     }

     public String getSignDate() {
          return signDate;
     }

     public void setSignDate(String signDate) {
          this.signDate = signDate;
     }

     public String getApplicant() {
          return applicant;
     }

     public void setApplicant(String applicant) {
          this.applicant = applicant;
     }

     public List<Space> getSpaces() {
          return spaces;
     }

     public void setSpaces(List<Space> spaces) {
          this.spaces = spaces;
     }

     public String getRemark() {
          return remark;
     }

     public void setRemark(String remark) {
          this.remark = remark;
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

     public String getEnterpriseName() {
          return enterpriseName;
     }

     public void setEnterpriseName(String enterpriseName) {
          this.enterpriseName = enterpriseName;
     }

     public Integer getResetFlag() {
          return resetFlag;
     }

     public void setResetFlag(Integer resetFlag) {
          this.resetFlag = resetFlag;
     }
}
