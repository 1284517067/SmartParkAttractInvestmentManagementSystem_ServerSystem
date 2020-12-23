package com.rzh.iot.service.impl;

import com.rzh.iot.dao.MenuDao;
import com.rzh.iot.dao.PositionDao;
import com.rzh.iot.model.MenuNode;
import com.rzh.iot.model.MenuPrivilege;
import com.rzh.iot.model.Position;
import com.rzh.iot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Autowired
    PositionDao positionDao;


    @Override
    public MenuNode getMenuNode(Long menuId) {

        return null;
    }

    @Override
    public List<MenuNode> getUserSlaveNodes(List<MenuNode> list, List<MenuNode> subList) {
        for(MenuNode node : list){
            List<MenuNode> childrenList = new ArrayList<>();
            for (MenuNode subNode : subList){
                if (subNode.getFatherNodeId() == node.getMenuId()){
                    childrenList.add(subNode);
                }
            }
            node.setNodes(childrenList);
        }
        return list;
    }

    @Override
    public List<MenuNode> getUserMenuTree(Long positionId) {
        List<MenuNode> menuTree = new ArrayList<>();
        Integer maxLevel = menuDao.getMaxMenuLevel();
        List<List> list = new ArrayList<>();
        for(int i = maxLevel;i >= 1 ; i--){
            List<MenuNode> nodeList = menuDao.getMenuNodeByLevelAndPositionId(i,positionId);
            list.add(nodeList);
        }
        for (int i = 1 ;i <= list.size()-1 ; i++){
            List<MenuNode> item = getUserSlaveNodes(list.get(i),list.get(i-1));
            list.set(i,item);
        }
        menuTree = list.get(list.size()-1);

        return menuTree;
    }

    @Override
    public List<MenuNode> getMenuList() {
        return menuDao.getMenuNodeList();
    }

    @Override
    public List<MenuNode> getMenuTreeData() {
        List<MenuNode> menuTree = new ArrayList<>();
        Integer maxLevel = menuDao.getMaxMenuLevel();
        List<List> lists = new ArrayList<>();
        for (int i = maxLevel; i >= 1 ; i--){
            List<MenuNode> nodeList = menuDao.getMenuNodeByLevel(i);
            lists.add(nodeList);
        }
        for (int i = 1 ; i <= lists.size()-1 ; i++){
            List<MenuNode> item = getUserSlaveNodes(lists.get(i),lists.get(i-1));
            lists.set(i,item);
        }
        menuTree = lists.get(lists.size()-1);
        return menuTree;
    }

    @Override
    public List<MenuNode> getAllMenuTreeData() {
        List<MenuNode> menuTree = new ArrayList<>();
        Integer maxLevel = menuDao.getMaxMenuLevel();
        List<List> lists = new ArrayList<>();
        for (int i = maxLevel; i >= 1 ; i--){
            List<MenuNode> nodeList = menuDao.getAllMenuNodeByLevel(i);
            lists.add(nodeList);
        }
        for (int i = 1 ; i <= lists.size()-1 ; i++){
            List<MenuNode> item = getUserSlaveNodes(lists.get(i),lists.get(i-1));
            lists.set(i,item);
        }
        menuTree = lists.get(lists.size()-1);
        return menuTree;
    }

    @Override
    public HashMap<String, String> updateMenuNode(MenuNode menuNode) {
        HashMap<String,String> map = new HashMap<>();
        if (menuNode.getFatherNodeId() != 0){
            menuNode.setLevel(menuDao.getLevelByMenuId(menuNode.getFatherNodeId())+1);
        }else {
            menuNode.setLevel(1);
        }
        if (menuNode.getMenuId() == 0){
            if (menuDao.isDuplication(menuNode) != 0){
                map.put("responseCode","400");
                map.put("msg","该菜单名称已存在");
                return map;
            }
            menuDao.createMenuNode(menuNode);
            map.put("responseCode","200");
            map.put("msg","创建成功");

        }else {
            if (menuDao.isExist(menuNode) == 0){
                map.put("responseCode","400");
                map.put("msg","该菜单不存在");
                return map;
            }
            menuDao.updateMenuNode(menuNode);
            map.put("responseCode","200");
            map.put("msg","更新成功");
        }
        return map;
    }

    @Override
    public HashMap<String, String> deleteMenu(Long menuId) {
        HashMap<String,String> map = new HashMap<>();
        MenuNode menuNode = new MenuNode();
        menuNode.setMenuId(menuId);
        if (menuDao.isExist(menuNode) == 0){
            map.put("responseCode","400");
            map.put("msg","该菜单不存在");
            return map;
        }
        menuDao.deleteMenu(menuId);
        map.put("responseCode","200");
        map.put("msg","删除成功");
        return map;
    }

    @Override
    public HashMap<String,Object>  getMenuPrivileges(Long positionId) {
        HashMap<String,Object> map = new HashMap<>();
        Position position = new Position();
        position.setPositionId(positionId);
        if (positionDao.isExist(position) == 0){
            map.put("responseCode", 400);
            map.put("msg","该职位不存在");
            return map;
        }
        if (menuDao.getCountOfMenuIdByPositionId(positionId) == 0){
            map.put("responseCode",200);
            map.put("privileges",new ArrayList<>());
            return map;
        }
        List<Long> list = menuDao.getMenuIdByPositionId(positionId);
        map.put("privileges",list);
        map.put("responseCode",200);
        return map;
    }

    @Override
    public HashMap<String, String> updateMenuPrivileges(Long positionId, List<Long> privileges) {
        HashMap<String,String> map = new HashMap<>();
        if (privileges.size() == 0){
            menuDao.deletePrivilegeByPositionId(positionId);
            map.put("responseCode","200");
            map.put("msg","保存成功");
            return map;
        }
        List<MenuPrivilege> menuPrivileges = new ArrayList<>();
        for (int i = 0 ; i < privileges.size() ; i++){
            MenuPrivilege menuPrivilege = new MenuPrivilege();
            menuPrivilege.setMenuId(privileges.get(i));
            menuPrivilege.setPositionId(positionId);
            menuPrivileges.add(menuPrivilege);
        }

        if (menuDao.getCountOfMenuIdByPositionId(positionId) != 0){
            menuDao.deletePrivilegeByPositionId(positionId);
        }

        menuDao.createMenuAuthority(menuPrivileges);
        map.put("responseCode","200");
        map.put("msg","保存成功");
        return map;
    }

    public List<MenuNode> getMasterNodeList(List<MenuNode> nodes){
        List<MenuNode> list =  new ArrayList<>();
        for (MenuNode node : nodes){
            if (node.getFatherNodeId() == 0){
                list.add(node);
            }
        }
        return list;
    }

}
