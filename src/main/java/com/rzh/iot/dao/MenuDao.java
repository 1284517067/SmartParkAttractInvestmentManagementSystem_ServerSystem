package com.rzh.iot.dao;

import com.rzh.iot.model.MenuNode;
import com.rzh.iot.model.MenuPrivilege;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MenuDao {
    @Select("select * from menu where menu_id = #{menuId}")
    MenuNode getMenuNodeByMenuId(Long menuId);

    @Select("select * from menu where father_node_id = #{menuId}")
    List<MenuNode> getSlaveNodesByMenuId(Long menuId);

    @Select("select * from menu where father_node_id is null")
    List<MenuNode> getMasterNode();

    @Select("select a.menu_id, a.menu_name , a.father_node_id ,a.status, a.icon , a.level , a.menu_path from menu a inner join menu_authority b on a.menu_id = b.menu_id where b.position_id = #{positionId}")
    List<MenuNode> getMenuNodeByPositionId(Long positionId);

    @Select("select a.menu_id , a.menu_name , a.father_node_id , a.status , a.icon , a.level , a.menu_path from menu a inner join menu_authority b on a.menu_id = b.menu_id where ( b.position_id = #{positionId} and a.level = #{level} and a.status != '已删除')")
    List<MenuNode> getMenuNodeByLevelAndPositionId(Integer level,Long positionId);

    @Select("select a.menu_id , a.menu_name , a.father_node_id , a.status , a.icon , a.level , a.menu_path from menu a inner join menu_authority b on a.menu_id = b.menu_id where ( b.position_id = #{positionId} and a.level = #{level})")
    List<MenuNode> getAllMenuNodeByLevelAndPositionId(Integer level,Long positionId);

    @Select("select max(level) from menu")
    int getMaxMenuLevel();

    @Select("select * from menu where level = #{level} and status != '已删除'")
    List<MenuNode> getMenuNodeByLevel(Integer level);

    @Select("select * from menu where level = #{level} and status != '已删除'")
    List<MenuNode> getAllMenuNodeByLevel(Integer level);

    @Select("select * from menu where status = '正常'")
    List<MenuNode> getMenuNodeList();

    @Select("select level from menu where menu_id = #{menuId}")
    Integer getLevelByMenuId(Long menuId);

    @Insert("insert into menu (menu_name,father_node_id,status,icon,level,menu_path) values (#{menuName},#{fatherNodeId},#{status},#{icon},#{level},#{menuPath})")
    int createMenuNode(MenuNode menuNode);

    @Update("update menu set menu_name = #{menuName} , father_node_id = #{fatherNodeId} , status = #{status} , icon = #{icon} ,level = #{level} , menu_path = #{menuPath} where menu_id = #{menuId}")
    int updateMenuNode(MenuNode menuNode);

    @Select("select count(menu_id) from menu where menu_name = #{menuName} and status != '已删除'")
    int isDuplication(MenuNode menuNode);

    @Select("select count(menu_id) from menu where menu_id = #{menuId} and status != '已删除'")
    int isExist(MenuNode menuNode);

    @Update("update menu set status = '已删除' where menu_id = #{menuId}")
    int deleteMenu(Long menuId);

    @Select("select menu_id from menu_authority where position_id = #{positionId}")
    List<Long> getMenuIdByPositionId(Long positionId);

    @Select("select count(*) from menu_authority where position_id = #{positionId}")
    int getCountOfMenuIdByPositionId(Long positionId);

    @Insert({"<script>",
            "insert into menu_authority (menu_id,position_id) values",
            "<foreach collection='privileges' item='item' index='index' separator=','>",
            "(#{item.menuId},#{item.positionId})",
            "</foreach>",
            "</script>"
    })
    int createMenuAuthority(@Param(value = "privileges")List<MenuPrivilege> privileges);

    @Delete("delete from menu_authority where position_id = #{positionId}")
    int deletePrivilegeByPositionId(Long positionId);
}

