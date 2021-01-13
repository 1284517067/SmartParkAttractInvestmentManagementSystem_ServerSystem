package com.rzh.iot.utils;

import com.rzh.iot.dao.IntentionAgreementDao;
import com.rzh.iot.dao.IntentionAgreementRoomDao;
import com.rzh.iot.dao.SpaceDao;
import com.rzh.iot.model.IntentionAgreement;
import com.rzh.iot.model.Space;
import com.rzh.iot.service.IntentionAgreementRoomService;
import com.rzh.iot.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    SpaceDao spaceDao;

    @Autowired
    IntentionAgreementDao intentionAgreementDao;

    @Autowired
    IntentionAgreementRoomService intentionAgreementRoomService;

    @Scheduled(cron = "0 0 12 * * ?")
    public void IntentionAgreementTask(){
        System.out.println("定时任务执行");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        List<IntentionAgreement> list = intentionAgreementDao.getApprovingIntentionAgreement();
        if (list == null){
            return;
        }
        try {
            for (IntentionAgreement item : list){
                Date deadline = format.parse(item.getDeadline());
                if (deadline.getTime() < currentDate.getTime()){
                    List<Space> spaces = intentionAgreementRoomService.getSpacesByIntentionAgreement(item.getFormId());
                    if (spaces != null){
                        for (Space space : spaces){
                            spaceDao.updateSpaceStatusBySpaceId(space.getSpaceId(),"待租");
                        }
                    }
                    intentionAgreementDao.updateIntentionAgreementResetFlag(item.getFormId(),new Integer(1));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
