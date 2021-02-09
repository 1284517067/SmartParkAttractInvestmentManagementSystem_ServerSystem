package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class LeaseContractRoom implements Serializable {

    private Long spaceId;

    private Long formId;

    public LeaseContractRoom() {
    }

    @Override
    public String toString() {
        return "LeaseContractRoom{" +
                "spaceId=" + spaceId +
                ", formId=" + formId +
                '}';
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }
}
