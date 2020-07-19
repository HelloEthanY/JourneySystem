package com.mine.org.controller;

import com.common.org.utils.ResponseCode;
import com.common.org.utils.ResultData;
import com.common.org.utils.md.DigestUtil;
import com.mine.org.entity.WorkAccountEntity;
import com.mine.org.service.account.WorkAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: YU
 * @Date: 17:02 2020/7/16
 * @Description: 账号信息接口
 */
@RestController
@RequestMapping(path = "/account")
@Api(value = "账号信息接口", tags = {"账号信息接口"})
public class WorkAccountController {

    @Resource
    private WorkAccountService workAccountService;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "新增账号信息接口", notes = "新增账号信息接口")
    @PostMapping(value = "/addAccount")
    public Object addEquip(@RequestBody WorkAccountEntity workAccountEntity) {
        try {
            workAccountEntity.setAccountPassword(DigestUtil.encode(workAccountEntity.getAccountPassword().getBytes()));
            workAccountService.saveWorkAccount(workAccountEntity);
            return ResultData.newSuccess(200, "新增成功！", null);
        } catch (Exception e) {
            return ResultData.newError(ResponseCode.COMMON_ERROR);
        }
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "账号信息列表", notes = "账号信息列表")
    @GetMapping(value = "/getAccountList") // required 设置参数可为空
    public Object getEquipList(@RequestParam(value = "size") int size,
                               @RequestParam(value = "page") int page,
                               @RequestParam(value = "content", required = false) String content) {
        try {
            if (content == null || "".equals(content)) {
                return ResultData.newSuccess(workAccountService.getWorkAccountList(size, page, ""));
            } else {
                return ResultData.newSuccess(workAccountService.getWorkAccountList(size, page, content));
            }
        } catch (Exception e) {
            return ResultData.newError(ResponseCode.COMMON_ERROR);
        }
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除账号信息接口", notes = "删除账号信息接口")
    @PostMapping(value = "/deleteAccount")
    public Object deleteEquip(@RequestParam(value = "id") String id) {
        try {
            workAccountService.deleteWorkAccount(id);
            return ResultData.newSuccess(200, "删除成功！", null);
        } catch (Exception e) {
            return ResultData.newError(ResponseCode.COMMON_ERROR);
        }
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "获取账号详情信息接口", notes = "获取账号详情信息接口")
    @PostMapping(value = "/getAccountInfoDetail")
    public Object getEquipInfoDetail(@RequestParam(value = "id") String id) {
        try {
            WorkAccountEntity accountDetail = workAccountService.getWorkAccountDetail(id);
            accountDetail.setAccountPassword(DigestUtil.decode(accountDetail.getAccountPassword().getBytes()));
            return ResultData.newSuccess(accountDetail);
        } catch (Exception e) {
            return ResultData.newError(ResponseCode.COMMON_ERROR);
        }
    }
}
