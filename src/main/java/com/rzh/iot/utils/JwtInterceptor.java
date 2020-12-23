package com.rzh.iot.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从http请求中取出token
        String token = request.getHeader("Authorization");
        System.out.println("Interceptor - token:"+  token);
        //如果不是映射方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            System.out.println("无注解，跳过验证");
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(JwtToken.class)){
            JwtToken jwtToken = method.getAnnotation(JwtToken.class);
            if (jwtToken.required()) {
                PrintWriter printWriter = null;
                JSONObject object = new JSONObject();
                try{
                    if (token == null){
                        String msg = "您还未登录，请先登录";
                        int code = 401;
                        object.put("responseCode",code);
                        object.put("msg",msg);
                        printWriter = response.getWriter();
                        printWriter.append(object.toJSONString());
                        printWriter.flush();
                        printWriter.close();
                        return false;
                    }
                    boolean result = TokenUtil.checkSign(token);
                    System.out.println("验证结果："+result);
                    if (result){
                        return true;
                    }else {
                        String msg = "登录已过期，请重新登录";
                        int code = 401;
                        object.put("responseCode",code);
                        object.put("msg",msg);
                        printWriter = response.getWriter();
                        printWriter.append(object.toJSONString());
                        printWriter.flush();
                        printWriter.close();
                        return false;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (printWriter != null){
                        printWriter.close();
                    }
                }
            }
        }
        return true;
    }
}
