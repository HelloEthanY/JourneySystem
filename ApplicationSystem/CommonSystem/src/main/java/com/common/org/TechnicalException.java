package com.common.org;

import com.common.org.utils.ResultData;

/**
 * @Author: YU
 * @Date: 13:07 2019/12/18
 * @Description: 创建Exception类
 */
public class TechnicalException extends RuntimeException {

    private ResultData resultData;

    public TechnicalException(ResultData resultData) {
        this.resultData = resultData;
    }

    public ResultData getResultData() {
        return resultData;
    }

    public void setResultData(ResultData resultData) {
        this.resultData = resultData;
    }
}
