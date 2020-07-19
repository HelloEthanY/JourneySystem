package com.mine.org.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mine.org.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: YU
 * @Date: 9:58 2019/12/18
 * @Description: Token 验证
 */
@Service
public class TokenService {
    /**
     * 获取token
     *
     * @param userEntity 用户实体类
     * @return
     */
    public String getToken(UserEntity userEntity) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 1000 * 30; // 一个小时的有效时间 60 * 60 * 1000
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(userEntity.getId()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(userEntity.getPassword()));
        return token;
    }
}
