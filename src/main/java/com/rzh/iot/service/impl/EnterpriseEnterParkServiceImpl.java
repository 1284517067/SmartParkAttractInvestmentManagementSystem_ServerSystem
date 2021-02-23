package com.rzh.iot.service.impl;

import com.rzh.iot.dao.EnterpriseDao;
import com.rzh.iot.model.Enterprise;
import com.rzh.iot.model.EnterpriseEnterPark;
import com.rzh.iot.model.Space;
import com.rzh.iot.service.EnterpriseEnterParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnterpriseEnterParkServiceImpl implements EnterpriseEnterParkService {

    @Autowired
    EnterpriseDao enterpriseDao;

    @Override
    public List<EnterpriseEnterPark> mountEnterpriseEnterParks(Long enterpriseId, List<Space> spaces, String recordDate) {

        List<EnterpriseEnterPark> enterpriseEnterParks = new ArrayList<>();
        for(Space space : spaces){
            EnterpriseEnterPark enterpriseEnterPark = new EnterpriseEnterPark();
            enterpriseEnterPark.setEnterpriseId(enterpriseId);
            enterpriseEnterPark.setSpaceId(space.getParkId());
            enterpriseEnterPark.setRecordDate(recordDate);
            enterpriseEnterParks.add(enterpriseEnterPark);
        }
        return enterpriseEnterParks;
    }

    @Override
    public List<EnterpriseEnterPark> filterEnterpriseEnterParks(Long enterpriseId, List<EnterpriseEnterPark> enterpriseEnterParks) {
        List<EnterpriseEnterPark> existedParks = enterpriseDao.getEnterpriseEnterParksByEnterpriseId(enterpriseId);
        for (EnterpriseEnterPark existedPark : existedParks){
            for (EnterpriseEnterPark park : enterpriseEnterParks){
                if (park.getSpaceId().equals(existedPark.getEnterpriseId())){
                    enterpriseEnterParks.remove(park);
                    break;
                }
            }
        }
        return enterpriseEnterParks;
    }
}
