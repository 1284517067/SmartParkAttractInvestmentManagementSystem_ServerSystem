package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class IndustryType implements Serializable {
    private Long industryTypeId;

    private String industryTypeName;

    private Long fatherNodeId;

    private String remark;

    private String parentTypeName;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentTypeName() {
        return parentTypeName;
    }

    public void setParentTypeName(String parentTypeName) {
        this.parentTypeName = parentTypeName;
    }

    private List<IndustryType> nodes;

    public List<IndustryType> getNodes() {
        return nodes;
    }

    public void setNodes(List<IndustryType> nodes) {
        this.nodes = nodes;
    }

    public IndustryType() {
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

    public Long getFatherNodeId() {
        return fatherNodeId;
    }

    public void setFatherNodeId(Long fatherNodeId) {
        this.fatherNodeId = fatherNodeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "IndustryType{" +
                "industryTypeId=" + industryTypeId +
                ", industryTypeName='" + industryTypeName + '\'' +
                ", fatherNodeId=" + fatherNodeId +
                ", remark='" + remark + '\'' +
                ", parentTypeName='" + parentTypeName + '\'' +
                ", status='" + status + '\'' +
                ", nodes=" + nodes +
                '}';
    }
}
