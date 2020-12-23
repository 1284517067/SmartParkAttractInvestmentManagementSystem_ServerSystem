package com.rzh.iot.service;

import com.rzh.iot.model.Space;

import java.util.HashMap;
import java.util.List;

public interface SpaceService {

    List<Space> getSpaceTreeData();

    List<Space> getSpaceTreeLeaf(Long spaceId);

    HashMap<String,String> updateSpace(Space space);

    HashMap<String,String> deleteSpace(Long spaceId);

    List<Space> getParkList();

}
