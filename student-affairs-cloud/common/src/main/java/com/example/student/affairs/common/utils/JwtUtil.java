package com.example.student.affairs.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET_KEY = "student-affairs";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 2小时过期

    // 生成 JWT Token
    public static String genToken(Map<String, Object> claims) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withPayload(claims)  // 设置负载
                .withIssuedAt(new Date())  // 设置签发时间
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // 设置过期时间
                .sign(algorithm);  // 使用签名算法生成 JWT
    }

    // 解析 JWT Token
    public static DecodedJWT decodeToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);  // 使用 HMAC256 签名算法
        return JWT.require(algorithm)
                .build()
                .verify(token);  // 验证 JWT 的有效性并解析
    }

    // 判断 token 是否过期
    public static boolean isTokenExpired(String token) {
        DecodedJWT decodedJWT = decodeToken(token);
        Date expiration = decodedJWT.getExpiresAt();  // 获取过期时间
        return expiration.before(new Date());  // 判断当前时间是否已过期
    }

    // 获取用户信息
    public static Map<String, Claim> parseToken(String token) {
        DecodedJWT decodedJWT = decodeToken(token);
        return decodedJWT.getClaims();  // 获取 JWT 负载中的数据
    }

}
