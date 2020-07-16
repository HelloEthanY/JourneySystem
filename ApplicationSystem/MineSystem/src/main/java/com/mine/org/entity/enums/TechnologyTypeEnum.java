package com.mine.org.entity.enums;

import lombok.AllArgsConstructor;

/**
 * @Author: YU
 * @Date: 9:49 2020/7/16
 * @Description: 技术类枚举
 */
@AllArgsConstructor
public enum TechnologyTypeEnum {

    // 类型为 安卓
    STATE_ANDROID(1, "Android"),
    // 类型为 Html
    STATE_HTML(2, "HTML"),
    // 类型为 Java
    STATE_JAVA(3, "Java"),
    // 类型为 Python
    STATE_PYTHON(4, "Python"),
    // 类型为 其他
    STATE_OTHER(5, "Other");

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
    }
}
