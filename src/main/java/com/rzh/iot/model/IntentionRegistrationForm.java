package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class IntentionRegistrationForm implements Serializable {

    private Long formId;

    private String formName;

    private String enterpriseName;

    private String enterpriseArea;

    private Long industryTypeId;

    private String industryTypeName;

    private String source;

    private String contact;

    private String contactTel;

    private String contactDepartment;

    private String contactPosition;

    private String qq;

    private String contactEmail;

    private String enterpriseTel;

    private String enterpriseUrl;

    private String enterpriseLegalPerson;

    private String registrationTime;

    private Long registeredCapital;

    private Long departmentId;

    private String departmentManager;

    private String applyDate;

    private String principal;

    private String contractType;

    private String businessRegistrationType;

    private String status;

    private String approvalStatus;

    public IntentionRegistrationForm() {

    }

    @Override
    public String toString() {
        return "IntentionRegistrationForm{" +
                "formId=" + formId +
                ", formName='" + formName + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseArea='" + enterpriseArea + '\'' +
                ", industryTypeId=" + industryTypeId +
                ", industryTypeName='" + industryTypeName + '\'' +
                ", source='" + source + '\'' +
                ", contact='" + contact + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", contactDepartment='" + contactDepartment + '\'' +
                ", contactPosition='" + contactPosition + '\'' +
                ", qq='" + qq + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", enterpriseTel='" + enterpriseTel + '\'' +
                ", enterpriseUrl='" + enterpriseUrl + '\'' +
                ", enterpriseLegalPerson='" + enterpriseLegalPerson + '\'' +
                ", registrationTime='" + registrationTime + '\'' +
                ", registeredCapital=" + registeredCapital +
                ", departmentId=" + departmentId +
                ", departmentManager='" + departmentManager + '\'' +
                ", applyDate='" + applyDate + '\'' +
                ", principal='" + principal + '\'' +
                ", contractType='" + contractType + '\'' +
                ", businessRegistrationType='" + businessRegistrationType + '\'' +
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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseArea() {
        return enterpriseArea;
    }

    public void setEnterpriseArea(String enterpriseArea) {
        this.enterpriseArea = enterpriseArea;
    }

    public Long getIndustryTypeId() {
        return industryTypeId;
    }

    public void setIndustryTypeId(Long industryTypeId) {
        this.industryTypeId = industryTypeId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getContactDepartment() {
        return contactDepartment;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setContactDepartment(String contactDepartment) {
        this.contactDepartment = contactDepartment;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getEnterpriseTel() {
        return enterpriseTel;
    }

    public void setEnterpriseTel(String enterpriseTel) {
        this.enterpriseTel = enterpriseTel;
    }

    public String getEnterpriseUrl() {
        return enterpriseUrl;
    }

    public void setEnterpriseUrl(String enterpriseUrl) {
        this.enterpriseUrl = enterpriseUrl;
    }

    public String getEnterpriseLegalPerson() {
        return enterpriseLegalPerson;
    }

    public void setEnterpriseLegalPerson(String enterpriseLegalPerson) {
        this.enterpriseLegalPerson = enterpriseLegalPerson;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Long getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Long registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getBusinessRegistrationType() {
        return businessRegistrationType;
    }

    public void setBusinessRegistrationType(String businessRegistrationType) {
        this.businessRegistrationType = businessRegistrationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getIndustryTypeName() {
        return industryTypeName;
    }

    public void setIndustryTypeName(String industryTypeName) {
        this.industryTypeName = industryTypeName;
    }
}
