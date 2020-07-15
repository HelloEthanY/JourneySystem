package com.course.org.controller;

import com.common.org.utils.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/test")
@Api(value = "测试接口", tags = {"测试接口"})
/*@ApiResponses({
        @ApiResponse(code = 200, message = "ok"),
        @ApiResponse(code = 205, message = "无数据", response = ResponseDto.class),
        @ApiResponse(code = 400, message = "失败！", response = ResponseDto.class),
        @ApiResponse(code = 500, message = "服务器错误", response = ResponseDto.class)
})*/
public class TestController {

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "测试接口", notes = "测试接口")
    @GetMapping(value = "/hello")
    public Object getTestHello() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", "12");
        map.put("school", "花溪二中");
        map.put("address", "贵州省贵阳市花溪区");
        return ResultData.newSuccess(map);
    }
}
