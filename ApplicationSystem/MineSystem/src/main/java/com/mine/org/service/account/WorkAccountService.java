package com.mine.org.service.account;

import com.mine.org.entity.WorkAccountEntity;

/**
 * @Author: YU
 * @Date: 10:29 2020/7/16
 * @Description: 工作账号
 */
public interface WorkAccountService {

    /* 保存或者更新 **/
    void saveWorkAccount(WorkAccountEntity entity) throws Exception;

    /* 删除账号 **/
    void deleteWorkAccount(String id) throws Exception;

    /* 获取账号列表 **/
    Object getWorkAccountList(int size, int page, String content) throws Exception;

    /* 获取账号详情 **/
    WorkAccountEntity getWorkAccountDetail(String id) throws Exception;
}
