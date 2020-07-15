package com.common.org.utils;

/**
 * @ClassName: ResponseCode
 * @Description: 返回值异常信息提示
 */
public enum ResponseCode {

    /**
     * 系统错误提示
     */
    COMMON_SUCCESS(200, "查询成功"),
    COMMON_CREATE_SUCCESS(200, "提交数据成功！"),
    COMMON_ERROR(400, "失败"),
    COMMON_DATABASE_EX(500, "数据库异常"),
    COMMON_JSON_PARSE_EX(-3, "数据格式错误"),
    COMMON_ILL_REQUEST_CALL(-4, "账号已在其它设备上登录，请重新登录"),
    COMMON_DEVICE_IDENTITY_IS_NULL(-5, "设备号不能为空"),
    COMMON_TOKEN_IS_NULL(-6, "未登录"),
    COMMON_TOKEN_IS_NULL_ID(-13, "ID不能为空！"),
    COMMON_SERVER_BUSY(-7, "服务器繁忙，请稍后重试"),
    COMMON_ID_VALIDATE_WRONG(-8, "提交参数异常"),
    COMMON_ILL_PARAMS(-9, "非法的请求参数"),
    COMMON_NO_PRIVILEGE(-10, "无权限访问"),
    COMMON_UNKOW_EX(-11, "未知错误"),
    COMMON_NOT_MOBILE(-12, "该设备不是移动设备"),
    COMMON_SMS_ALREADY_SEND(0, "验证码已发送，请注意查收短信"),

    /**
     * 业务功能错误提示
     */
    // 用户
    USER_SMS_SEND_EX(1001, "短信发送异常，请联系管理员后重试"),
    USER_MOBILE_PATTERN_WRONG(1002, "用户不正确"),
    USER_MOBILE_ALREADY_REGISTER(1003, "该手机号码已被注册"),
    USER_MOBILE_VALID_CODE_WRONG(1004, "验证码不正确"),
    USER_REGISTER_FAIL(1005, "注册失败，请联系管理员"),
    USER_ACPASS_WRONG(1006, "密码不正确"),
    USER_LOGIN_EX(1007, "登录异常！"),
    USER_UN_LOGIN(1008, "长时间未登录，请重新登陆"),
    USER_NOT_EXIST(1009, "用户不存在");

    public int code;
    public String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}