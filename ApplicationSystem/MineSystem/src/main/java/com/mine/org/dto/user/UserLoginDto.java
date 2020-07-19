package com.mine.org.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@ApiModel(description = "登陆返回信息")
@Builder
@Data
public class UserLoginDto {
    
    @ApiModelProperty(value = "权限", position = 1, example = "super")
    private String level;
    /* 头像地址 **/
    @ApiModelProperty(value = "头像地址", position = 1, example = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2841348965,2123181886&fm=26&gp=0.jpg")
    private String headImg;
    /* 电话 **/
    @ApiModelProperty(value = "电话", position = 1, example = "13007825658")
    private String phone;
    /* 昵名 **/
    @ApiModelProperty(value = "昵名", position = 1, example = "hello")
    private String nickName;
    /* 密码 **/
    @ApiModelProperty(value = "密码", position = 1, example = "...")
    private String password;
    /* 地址 **/
    @ApiModelProperty(value = "地址", position = 1, example = "贵州省贵阳市南明区")
    private String address;
    /* 用户名 **/
    @ApiModelProperty(value = "用户名", position = 1, example = "journey")
    private String userName;

}
