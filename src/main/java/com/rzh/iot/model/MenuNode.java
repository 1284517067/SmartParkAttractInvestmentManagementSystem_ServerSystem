package com.rzh.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MenuNode implements Serializable {

    private long menuId;

    private String menuName;

    private long fatherNodeId;

    private String status;

    private String icon;

    private String menuPath;

    private Integer level;

    private List<MenuNode> nodes = new ArrayList<>();

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }



    public MenuNode() {
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public long getFatherNodeId() {
        return fatherNodeId;
    }

    public void setFatherNodeId(long fatherNodeId) {
        this.fatherNodeId = fatherNodeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<MenuNode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "MenuNode{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", fatherNodeId=" + fatherNodeId +
                ", status='" + status + '\'' +
                ", icon='" + icon + '\'' +
                ", menuPath='" + menuPath + '\'' +
                ", level=" + level +
                ", nodes=" + nodes.toString() +
                '}';
    }
}
