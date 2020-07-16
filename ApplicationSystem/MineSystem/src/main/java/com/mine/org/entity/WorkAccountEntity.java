package com.mine.org.entity;

import com.common.org.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Author: YU
 * @Date: 9:20 2020/7/16
 * @Description: 工作账号
 */
@Entity(name = "work_account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkAccountEntity extends BaseEntity {

    /* 账号内容 **/
    @Column(name = "work_account")
    private String workAccount;

    /* 简介**/
    @Column(name = "introduce")
    private String introduce;

    /* 账号名称 **/
    @Column(name = "account_name", length = 100)
    private String accountName;

    /* 用户名 **/
    @Column(name = "user_name", length = 100)
    private String userName;

    /* 密码 **/
    @Column(name = "account_password", length = 50)
    private String accountPassword;

    /* 标题 **/
    @Column(name = "account_title")
    private String accountTitle;
}
