package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class Enterprise implements Serializable {

    private Long enterpriseId;

    private String enterpriseName;

    private String status;

    private String enterpriseNature;

    private String enterTime;

    private String officeAddress;

    private String enterpriseLegalPerson;

    private String registrationTime;

    private String contact;

    private String contactTel;

    private String contactDepartment;

    private String contactPosition;

    private String enterpriseEmail;

    private Long registeredCapital;

    private Long industryTypeId;

    private String industryTypeName;

    private String enterpriseArea;

    private String businessRegistrationType;

    private String enterpriseIntroduction;

    private Long enterPark;

    private String spaceName;

    private String recordDate;

    private String contractType;

    private String enterpriseTel;

    private String source;

    private String qq;

    private List<EnterpriseEnterPark> enterParks;

    public Enterprise() {
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "enterpriseId=" + enterpriseId +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", status='" + status + '\'' +
                ", enterpriseNature='" + enterpriseNature + '\'' +
                ", enterTime='" + enterTime + '\'' +
                ", officeAddress='" + officeAddress + '\'' +
                ", enterpriseLegalPerson='" + enterpriseLegalPerson + '\'' +
                ", registrationTime='" + registrationTime + '\'' +
                ", contact='" + contact + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", contactDepartment='" + contactDepartment + '\'' +
                ", contactPosition='" + contactPosition + '\'' +
                ", enterpriseEmail='" + enterpriseEmail + '\'' +
                ", registeredCapital=" + registeredCapital +
                ", industryTypeId=" + industryTypeId +
                ", industryTypeName='" + industryTypeName + '\'' +
                ", enterpriseArea='" + enterpriseArea + '\'' +
                ", businessRegistrationType='" + businessRegistrationType + '\'' +
                ", enterpriseIntroduction='" + enterpriseIntroduction + '\'' +
                ", enterPark=" + enterPark +
                ", spaceName='" + spaceName + '\'' +
                ", recordDate='" + recordDate + '\'' +
                ", contractType='" + contractType + '\'' +
                ", enterpriseTel='" + enterpriseTel + '\'' +
                ", source='" + source + '\'' +
                ", qq='" + qq + '\'' +
                ", enterParks=" + enterParks +
                '}';
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnterpriseNature() {
        return enterpriseNature;
    }

    public void setEnterpriseNature(String enterpriseNature) {
        this.enterpriseNature = enterpriseNature;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
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

    public String getEnterpriseEmail() {
        return enterpriseEmail;
    }

    public void setEnterpriseEmail(String enterpriseEmail) {
        this.enterpriseEmail = enterpriseEmail;
    }

    public Long getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Long registeredCapital) {
        this.registeredCapital = registeredCapital;
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

    public String getEnterpriseArea() {
        return enterpriseArea;
    }

    public void setEnterpriseArea(String enterpriseArea) {
        this.enterpriseArea = enterpriseArea;
    }

    public String getBusinessRegistrationType() {
        return businessRegistrationType;
    }

    public void setBusinessRegistrationType(String businessRegistrationType) {
        this.businessRegistrationType = businessRegistrationType;
    }

    public String getEnterpriseIntroduction() {
        return enterpriseIntroduction;
    }

    public void setEnterpriseIntroduction(String enterpriseIntroduction) {
        this.enterpriseIntroduction = enterpriseIntroduction;
    }

    public List<EnterpriseEnterPark> getEnterParks() {
        return enterParks;
    }

    public void setEnterParks(List<EnterpriseEnterPark> enterParks) {
        this.enterParks = enterParks;
    }

    public Long getEnterPark() {
        return enterPark;
    }

    public void setEnterPark(Long enterPark) {
        this.enterPark = enterPark;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getEnterpriseTel() {
        return enterpriseTel;
    }

    public void setEnterpriseTel(String enterpriseTel) {
        this.enterpriseTel = enterpriseTel;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
