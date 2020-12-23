package com.rzh.iot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rzh.iot.model.User;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.DigestUtils;

import java.util.Date;

public class JwtUtil {


    /**
     *  过期时间30秒
     * */
    private static final long EXPIRE_TIME = 30*1000;
    /**
     *  jwt秘钥
     * */
    private static final String TOKEN_SECRET = "jwt_secret";

    /**
     * 生成token
     * @param  user
     * @return
     * */

    public static String createToken(User user){
        try{
            Date now = new Date(System.currentTimeMillis());
            JwtBuilder builder = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date())
                    .setAudience(user.getUsername())
                    .signWith(SignatureAlgorithm.HS256,TOKEN_SECRET)
                    .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                    .setNotBefore(now);
         return builder.compact();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    /**
     * 获取用户ID
     * @param decodedJWT
     * @return
     * */
    public static String getUserName(DecodedJWT decodedJWT){
        return decodedJWT.getClaim("username").asString();
    }

}
