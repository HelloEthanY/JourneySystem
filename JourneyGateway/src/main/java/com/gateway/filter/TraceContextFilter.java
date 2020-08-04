package com.gateway.filter;

// @Order(Ordered.HIGHEST_PRECEDENCE + 8)
// @ConditionalOnClass(WebMvcConfigurer.class)
public class TraceContextFilter //extends OncePerRequestFilter
{

    /*@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //请求头传入存在以请求头传入的为准，不然以X-B3-TraceId为
    *//*    String app_trace_id = StringUtils.defaultString(request.getHeader(TraceConstant.HTTP_HEADER_TRACE_ID), MDC.get(TraceConstant.LOG_B3_TRACEID));
        //未经过HandlerInterceptor的设置，但是有请求头，重新设置
        if (StringUtils.isNotEmpty(app_trace_id)) {
            MDC.put(TraceConstant.LOG_TRACE_ID, app_trace_id);
        }
        filterChain.doFilter(request, response);*//*
    }*/
}