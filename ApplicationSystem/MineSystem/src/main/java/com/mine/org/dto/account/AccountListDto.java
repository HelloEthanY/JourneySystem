package com.mine.org.dto.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: YU
 * @Date: 14:51 2020/7/16
 * @Description: 账号列表
 */
@ApiModel(description = "账号")
@Data
@Builder
public class AccountListDto {

    @ApiModelProperty(value = "ID", position = 1, example = "1247815924@qq.com")
    private String id;

    /* 账号内容 **/
    @ApiModelProperty(value = "账号内容", position = 1, example = "1247815924@qq.com")
    private String workAccount;

    /* 简介 **/
    @ApiModelProperty(value = "简介", position = 1, example = "这是关于一个Android模拟器的账号")
    private String introduce;

    /* 账号名称 **/
    @ApiModelProperty(value = "账号名称", position = 1, example = "EthanYQY")
    private String accountName;

    /* 用户名 **/
    @ApiModelProperty(value = "用户名", position = 1, example = "EthanYQY")
    private String userName;

    /* 标题 **/
    @ApiModelProperty(value = "标题", position = 1, example = "Genymotion")
    private String accountTitle;

}
