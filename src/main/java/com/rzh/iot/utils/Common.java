package com.rzh.iot.utils;

import com.rzh.iot.dao.IntentionAgreementDao;
import com.rzh.iot.dao.IntentionRegistrationFormDao;
import com.rzh.iot.dao.LeaseContractDao;
import com.rzh.iot.service.IntentionRegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Common {

    @Autowired
    IntentionRegistrationFormDao intentionRegistrationFormDao;

    @Autowired
    IntentionAgreementDao intentionAgreementDao;

    @Autowired
    LeaseContractDao leaseContractDao;


    public String mountFormName(String formName,String contractType){
        String str = "(?<=\\()\\d+(?=\\))";
        int count = 0;
        switch (contractType){
            case "意向登记":
                count = intentionRegistrationFormDao.isNameExist(formName);
                break;
            case "意向协议":
                count = intentionAgreementDao.isNameExist(formName);
                break;
            case "租赁合同":
                count = leaseContractDao.isNameExist(formName);
        }
        if (count != 0){
            Pattern r = Pattern.compile(str);
            Matcher matcher = r.matcher(formName);
            boolean flag = matcher.find();
            if (flag){
                Integer num = Integer.parseInt(matcher.group());
                num++;
                formName = formName.replaceAll(str, num.toString());
            }else {
                formName += "(1)";
            }
        }else {
            return formName;
        }
        return mountFormName(formName,contractType);
    }
}
