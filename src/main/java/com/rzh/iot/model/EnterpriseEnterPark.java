package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class EnterpriseEnterPark implements Serializable {

    private Long enterpriseId;

    private String enterpriseName;

    private Long spaceId;

    private String spaceName;

    private String recordDate;

    public EnterpriseEnterPark() {
    }

    @Override
    public String toString() {
        return "EnterpriseEnterPark{" +
                "enterpriseId=" + enterpriseId +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", spaceId=" + spaceId +
                ", spaceName='" + spaceName + '\'' +
                ", recordDate='" + recordDate + '\'' +
                '}';
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }
}
