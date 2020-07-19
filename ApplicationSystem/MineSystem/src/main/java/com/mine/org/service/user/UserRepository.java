package com.mine.org.service.user;

import com.common.org.base.BaseRepository;
import com.mine.org.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author: YU
 * @Date: 11:19 2020/7/19
 * @Description: 用户表
 */
@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
}
