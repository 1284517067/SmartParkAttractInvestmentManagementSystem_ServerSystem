package com.rzh.iot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rzh.iot.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.util.Date;

public class TokenUtil {

    /**
     *  过期时间30分钟
     * */
    private static final long EXPIRE_TIME = 30*1000;
    /**
     *  jwt秘钥
     * */
    private static final String TOKEN_SECRET = "jwt_secret";

    /**
     * 生成签名，30分钟后过期
     * @param user
     * @return
     * */

    public static String sign(User user){
        String token = null;
        try{
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            return JWT.create()
                    .withAudience(user.getUsername())
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .sign(algorithm);
            /*
            JwtBuilder builder = Jwts.builder()
                    .setId(user.getUsername())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256,TOKEN_SECRET)
                    .setExpiration(date);
            return builder.compact();*/

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据token获取username
     * @param token
     * @return
     * */
    public static String getUserId(String token) {
        try {
            String username = JWT.decode(token).getAudience().get(0);
            return username;
        }catch (JWTDecodeException e){
            return null;
        }
    }

    /**
     * 校验token
     * */
    public static boolean checkSign(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            return true;
        }catch (JWTVerificationException e) {
            return false;
        }
    }
}
