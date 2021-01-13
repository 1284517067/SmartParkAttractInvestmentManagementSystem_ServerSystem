package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class IntentionAgreementRoom implements Serializable {

    private Long formId;

    private Long spaceId;

    public IntentionAgreementRoom() {
    }

    @Override
    public String toString() {
        return "IntentionAgreementRoom{" +
                "formId=" + formId +
                ", spaceId=" + spaceId +
                '}';
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }
}
