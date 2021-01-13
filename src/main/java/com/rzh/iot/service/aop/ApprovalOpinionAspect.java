package com.rzh.iot.service.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*@Component
@Aspect
public class ApprovalOpinionAspect {

    private static final String POINT_CUT = "ApprovalOpinionAspect()";

    @Pointcut("execution(* com.rzh.iot.service.ApprovalOpinionService.updateOpinion(..))")
    public void ApprovalOpinionAspect(){

    }

    @AfterReturning(value = POINT_CUT,returning = "keys")
    public JSONObject scanApprovalOpinion(JoinPoint joinPoint,Object keys){
       if (keys instanceof JSONObject){
           JSONObject object = (JSONObject) keys;
       }
    }
}*/
