package com.common.org.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author: YU
 * @Date: 9:37 2020/7/19
 * @Description: 不允许单独访问model 只能通过gateway网关进行访问 拦截器
 * 其实像这样的拦截器 也可以通过是否开放端口号来拦截控制
 */
public class GlobalInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String course = request.getHeader("from");
        if (!StringUtils.isNotBlank(course) || !course.equals("gateway")) {
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject json = new JSONObject();
            if ("token".equals(course)) {
                json.put("message", "无效token！");
                json.put("code", 200);
            } else {
                json.put("message", "访问错误！");
                json.put("code", 404);
            }
            json.put("result", null);
            writer.write(JSON.toJSONString(json, SerializerFeature.WriteMapNullValue));
            writer.flush();
            writer.close();
            return false;
        }
        return true;
    }
}
