package com.rzh.iot.service.impl;

import com.rzh.iot.dao.SpaceDao;
import com.rzh.iot.model.Space;
import com.rzh.iot.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SpaceServiceImpl implements SpaceService {

    /**
     * 注入持久层依赖
     * */
    @Autowired
    SpaceDao spaceDao;

    /**
     * 获取空间树第一层节点数据
     * @return spaces
     * */
    @Override
    public List<Space> getSpaceTreeData() {
        List<Space> spaces = spaceDao.getSpaceList();
        for (Space space:spaces){
            if (space.getParentNodeId() != null && space.getParentNodeId() != 0){
                space.setParentName(spaceDao.getSpaceNameBySpaceId(space.getParentNodeId()));
            }
        }
        return spaces;
    }

    /**
     * 获取懒加载空间树叶子节点数据
     * @param spaceId
     * @return spaces
     * */
    @Override
    public List<Space> getSpaceTreeLeaf(Long spaceId) {
        List<Space> spaces = spaceDao.getSpaceListByParentNodeId(spaceId);
        for (Space space:spaces){
            if (space.getParentNodeId() != 0){
                space.setParentName(spaceDao.getSpaceNameBySpaceId(space.getParentNodeId()));
            }
        }
        return spaces;
    }

    /**
     * 新增/更新空间节点
     * @param space
     * @return map
     * */
    @Override
    public HashMap<String, String> updateSpace(Space space) {
        HashMap<String,String> map = new HashMap<>();
        if (space.getSpaceId() == null){
            /**
             * 新增
             * */
            if (spaceDao.isSpaceExist(space) != 0){
                map.put("responseCode","400");
                map.put("msg","该层空间下已存在此空间名称");
                return map;
            }
            spaceDao.createSpace(space);
            map.put("responseCode","200");
            map.put("msg","保存成功");

        }else {
            /**
             * 更新
             * */
            if (spaceDao.isSpaceIdExist(space.getSpaceId()) == 0){
                map.put("responseCode","400");
                map.put("msg","此空间不存在");
                return map;
            }
            spaceDao.updateSpace(space);
            map.put("responseCode","200");
            map.put("msg","保存成功");
        }
        return map;
    }

    @Override
    public HashMap<String, String> deleteSpace(Long spaceId) {
        HashMap<String,String> map = new HashMap<>();
        if (spaceDao.isSpaceIdExist(spaceId) == 0){
            map.put("responseCode","400");
            map.put("msg","此空间不存在");
            return map;
        }
        spaceDao.deleteSpace(spaceId);
        map.put("responseCode","200");
        map.put("msg","删除成功");
        return map;
    }

    @Override
    public List<Space> getParkList() {
        return spaceDao.getParkList();
    }

    private List<Space> mountSpaceTree(List<Space> list){
        List<Space> park = new ArrayList<>();
        List<Space> build = new ArrayList<>();
        List<Space> floor = new ArrayList<>();
        List<Space> room = new ArrayList<>();
        for (Space item:list){
            switch (item.getSpaceType()){
                case "园区":
                    park.add(item);
                    break;
                case "楼栋":
                    build.add(item);
                    break;
                case "楼层":
                    floor.add(item);
                    break;
                case "房间":
                    room.add(item);
                    break;
            }
        }

        return list;
    }
}
