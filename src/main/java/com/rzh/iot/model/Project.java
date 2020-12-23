package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Project implements Serializable {
    private Long projectId;

    private String enterpriseName;

    private String enterpriseArea;

    private Long industryTypeId;

    private String industryTypeName;

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

    private String departmentName;

    private String principal;

    private String source;

    private String status;

    public Project() {
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseArea='" + enterpriseArea + '\'' +
                ", industryTypeId=" + industryTypeId +
                ", industryTypeName='" + industryTypeName + '\'' +
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
                ", departmentName='" + departmentName + '\'' +
                ", principal='" + principal + '\'' +
                ", source='" + source + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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

    public String getIndustryTypeName() {
        return industryTypeName;
    }

    public void setIndustryTypeName(String industryTypeName) {
        this.industryTypeName = industryTypeName;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
