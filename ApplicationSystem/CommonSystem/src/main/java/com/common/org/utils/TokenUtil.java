package com.common.org.utils;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: YU
 * @Date: 9:56 2019/12/18
 * @Description: token 工具类
 */
public class TokenUtil {

    public static String getTokenUserId() {
        if (getRequest() == null) {
            return "";
        }
        String token = getRequest().getHeader("token");// 从 http 请求头中取出 token
        return JWT.decode(token).getAudience().get(0);
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}
