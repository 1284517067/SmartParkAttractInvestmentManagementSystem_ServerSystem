package com.rzh.iot.service;

import com.rzh.iot.model.EnterpriseEnterPark;
import com.rzh.iot.model.Space;

import java.util.List;

public interface EnterpriseEnterParkService {

    List<EnterpriseEnterPark> mountEnterpriseEnterParks(Long enterpriseId,List<Space> spaces, String recordDate);

    List<EnterpriseEnterPark> filterEnterpriseEnterParks(Long enterpriseId,List<EnterpriseEnterPark> enterpriseEnterParks);
}
