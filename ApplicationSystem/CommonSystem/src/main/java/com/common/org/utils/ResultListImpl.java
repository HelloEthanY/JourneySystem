package com.common.org.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: YU
 * @Date: 14:32 2020/5/25
 * @Description: Impl层得数据返回格式
 */
@Getter
@Setter
public class ResultListImpl<T> {

    private long count;
    private int size;
    private int page;
    private T data;

    public ResultListImpl() {
        this.count = 0;
        this.size = 20;
        this.page = 1;
        this.data = null;
    }

    public ResultListImpl(long count, int size, int page, T data) {
        this.count = count;
        this.size = size;
        this.page = page;
        this.data = data;
    }

    public static <T> ResultListImpl<T> newResult(long count, int size, int page, T data) {
        return new ResultListImpl<>(count, size, page, data);
    }
}
