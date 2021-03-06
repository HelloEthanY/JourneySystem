package com.common.org.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.common.org.TechnicalException;
import com.common.org.inf.PassToken;
import com.common.org.inf.UserLoginToken;
import com.common.org.utils.ResponseCode;
import com.common.org.utils.ResultData;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: YU
 * @Date: 11:45 2019/12/18
 * @Description: 拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    // @Resource
    //UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new TechnicalException(ResultData.newError(ResponseCode.COMMON_TOKEN_IS_NULL));
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new TechnicalException(ResultData.newError(401, "无用户"));
                }
                // UserEntity user = userService.getUserById(userId);
               /* if (user == null) {
                    throw new TechnicalException(ResultData.newError(401, "用户不存在，请重新登录"));
                }*/
                // 验证 token
                // JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
              /*  try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new TechnicalException(ResultData.newError(203, "token过期"));
                }*/
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
