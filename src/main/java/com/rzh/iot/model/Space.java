package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class Space implements Serializable {

    private Long spaceId;

    private String spaceName;

    private String spaceAddress;

    private Long areaCovered;

    private Long floorSpace;

    private String spaceType;

    private Double price;

    private Long parentNodeId;

    private String parentName;

    private Long leaseArea;

    private String status;

    private List<Space> children;

    private String parkName;

    private String buildingName;

    private String floorName;

    private Long enterpriseId;

    private String enterpriseName;

    public Space() {
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getLeaseArea() {
        return leaseArea;
    }

    public void setLeaseArea(Long leaseArea) {
        this.leaseArea = leaseArea;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getSpaceAddress() {
        return spaceAddress;
    }

    public void setSpaceAddress(String spaceAddress) {
        this.spaceAddress = spaceAddress;
    }

    public Long getAreaCovered() {
        return areaCovered;
    }

    public void setAreaCovered(Long areaCovered) {
        this.areaCovered = areaCovered;
    }

    public Long getFloorSpace() {
        return floorSpace;
    }

    public void setFloorSpace(Long floorSpace) {
        this.floorSpace = floorSpace;
    }

    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(Long parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public List<Space> getChildren() {
        return children;
    }

    public void setChildren(List<Space> children) {
        this.children = children;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
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

    @Override
    public String toString() {
        return "Space{" +
                "spaceId=" + spaceId +
                ", spaceName='" + spaceName + '\'' +
                ", spaceAddress='" + spaceAddress + '\'' +
                ", areaCovered=" + areaCovered +
                ", floorSpace=" + floorSpace +
                ", spaceType='" + spaceType + '\'' +
                ", price=" + price +
                ", parentNodeId=" + parentNodeId +
                ", parentName='" + parentName + '\'' +
                ", leaseArea=" + leaseArea +
                ", status='" + status + '\'' +
                ", children=" + children +
                ", parkName='" + parkName + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", floorName='" + floorName + '\'' +
                ", enterpriseId=" + enterpriseId +
                ", enterpriseName='" + enterpriseName + '\'' +
                '}';
    }
}
