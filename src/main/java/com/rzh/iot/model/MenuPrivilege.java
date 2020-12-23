package com.rzh.iot.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MenuPrivilege implements Serializable {

    private Long menuId;

    private Long positionId;

    @Override
    public String toString() {
        return "MenuPrivileges{" +
                "menuId=" + menuId +
                ", positionId=" + positionId +
                '}';
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public MenuPrivilege() {
    }
}
