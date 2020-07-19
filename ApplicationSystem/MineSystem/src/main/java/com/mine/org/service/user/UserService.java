package com.mine.org.service.user;

import com.mine.org.dto.user.UserLoginDto;
import com.mine.org.entity.UserEntity;

/**
 * @Author: YU
 * @Date: 11:19 2020/7/19
 * @Description: 用户表
 */
public interface UserService {

    /* 登陆 **/
    UserEntity login(String userName) throws Exception;

    /* 获取用户信息 **/
    UserLoginDto getUserInfo(String userName) throws Exception;

    /* 获取用户列表 **/
    Object getUserList(int page, int size, String content) throws Exception;

    /* 创建或者更新用户 **/
    void save(UserEntity userEntity) throws Exception;

    /* 删除用户 **/
    void deleteUser(String id) throws Exception;
}
