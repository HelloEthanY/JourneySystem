package com.mine.org.entity.enums;

import lombok.AllArgsConstructor;

/**
 * @Author: YU
 * @Date: 10:01 2020/7/16
 * @Description: 工作状态
 */
@AllArgsConstructor
public enum WorkStatusEnum {

    STATE_NO(1, "未完成"),

    STATE_LOADING(2, "进行中..."),

    STATE_OVER(3, "完成");

    private int code;

    private String des;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }}
