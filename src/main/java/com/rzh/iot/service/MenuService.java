package com.rzh.iot.service;

import com.rzh.iot.model.MenuNode;

import java.util.HashMap;
import java.util.List;

public interface MenuService {
     MenuNode getMenuNode(Long menuId);

     List<MenuNode> getUserSlaveNodes(List<MenuNode> list, List<MenuNode> subList);

     List<MenuNode> getUserMenuTree(Long positionId);

     List<MenuNode> getMenuList();

     List<MenuNode> getMenuTreeData();

     List<MenuNode> getAllMenuTreeData();

     HashMap<String,String> updateMenuNode(MenuNode menuNode);

     HashMap<String,String> deleteMenu(Long menuId);

     HashMap<String,Object> getMenuPrivileges(Long positionId);

     HashMap<String,String> updateMenuPrivileges(Long positionId,List<Long> privileges);
}

