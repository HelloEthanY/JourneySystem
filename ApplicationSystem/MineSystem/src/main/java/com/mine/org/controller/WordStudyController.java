package com.mine.org.controller;

import com.common.org.TechnicalException;
import com.common.org.utils.ResultData;
import com.mine.org.entity.WordStudyEntity;
import com.mine.org.entity.enums.WorkStatusEnum;
import com.mine.org.service.word.WordStudyService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: YU
 * @Date: 16:29 2020/7/20
 * @Description: 单词控制台
 */
@RestController
@RequestMapping(path = "/wordStudy")
@Api(value = "单词信息接口", tags = {"单词信息接口"})
public class WordStudyController {

    @Resource
    private WordStudyService wordStudyService;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "新增单词信息接口", notes = "新增单词信息接口")
    @PostMapping(path = "/saveWord")
    public Object saveWord(@RequestBody WordStudyEntity wordStudyEntity) {
        try {
            int saveState = wordStudyService.saveWord(wordStudyEntity);
            if (saveState == 0) {
                return ResultData.newSuccess("保存成功！", null);
            } else {
                return ResultData.newError(202, "有相同单词！");
            }
        } catch (Exception e) {
            throw new TechnicalException(ResultData.newError(400, e.getMessage()));
        }
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "获取单词列表", notes = "获取单词列表信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "返回数据长度", required = true),
            @ApiImplicitParam(name = "page", value = "页数", required = true),
            @ApiImplicitParam(name = "content", value = "模糊查询条件")
    })
    @GetMapping(path = "/getWordList")
    public Object getWordList(@RequestParam(value = "page") int page,
                              @RequestParam(value = "size") int size,
                              @RequestParam(value = "content", required = false) String content,
                              @RequestParam(value = "state", required = false) WorkStatusEnum state) {
        try {
            return ResultData.newSuccess(wordStudyService.getWordList(page, size, content, state));
        } catch (Exception e) {
            throw new TechnicalException(ResultData.newError(400, e.getMessage()));
        }
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取单词详情", notes = "获取单词详情信息接口")
    @ApiImplicitParam(name = "id", value = "单词id", required = true)
    @GetMapping(path = "/getWordDetail")
    public Object getWordDetail(@RequestParam(value = "id") String id) {
        try {
            return ResultData.newSuccess(wordStudyService.getWordDetail(id));
        } catch (Exception e) {
            throw new TechnicalException(ResultData.newError(400, e.getMessage()));
        }
    }
}
