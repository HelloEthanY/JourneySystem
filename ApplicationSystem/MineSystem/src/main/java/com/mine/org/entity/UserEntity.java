package com.mine.org.entity;

import com.common.org.base.BaseAuditorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author: YU
 * @Date: 11:12 2020/7/19
 * @Description: 用户表
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "mine_user")
public class UserEntity extends BaseAuditorEntity {
    /* 头像地址 **/
    @Column(name = "head_img")
    private String headImg;
    /* 电话 **/
    @Column(name = "phone", length = 20)
    private String phone;
    /* 昵名 **/
    @Column(name = "nick_name", length = 50)
    private String nickName;
    /* 密码 **/
    @Column(name = "password", length = 100)
    private String password;
    /* 地址 **/
    @Column(name = "address", length = 100)
    private String address;
    /* 用户名 **/
    @Column(name = "user_name", length = 50)
    private String userName;
    /* 关联 权限表 **/
    @OneToOne(targetEntity = RoleEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;
}
