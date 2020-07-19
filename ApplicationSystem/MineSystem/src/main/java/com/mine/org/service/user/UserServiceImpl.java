package com.mine.org.service.user;

import com.common.org.base.BaseService;
import com.common.org.utils.ResultListImpl;
import com.mine.org.dto.user.UserListDto;
import com.mine.org.dto.user.UserLoginDto;
import com.mine.org.entity.QUserEntity;
import com.mine.org.entity.UserEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

/**
 * @Author: YU
 * @Date: 11:20 2020/7/19
 * @Description: 用户表
 */
@Service
public class UserServiceImpl extends BaseService<UserEntity> implements UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * 登陆
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public UserEntity login(String userName) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        return factory.selectFrom(qUserEntity)
                .where(qUserEntity.userName.eq(userName))
                .fetchFirst();
    }

    /**
     * 通过用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public UserLoginDto getUserInfo(String userName) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        UserEntity userEntity = factory.selectFrom(qUserEntity)
                .where(qUserEntity.userName.eq(userName))
                .fetchFirst();
        if (userEntity != null) {
            return UserLoginDto.builder()
                    .userName(userEntity.getUserName())
                    .phone(userEntity.getPhone())
                    .password(userEntity.getPassword())
                    .nickName(userEntity.getNickName())
                    .level(userEntity.getRoleEntity().getLevel())
                    .headImg(userEntity.getHeadImg())
                    .address(userEntity.getAddress())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 获取用户列表
     *
     * @param page    页码
     * @param size    大小
     * @param content 模糊查询
     * @return 用户列表
     */
    @Override
    public Object getUserList(int page, int size, String content) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!"".equals(content)) {
            booleanBuilder.and(qUserEntity.userName.like("%" + content + "%"));
            booleanBuilder.or(qUserEntity.nickName.like("%" + content + "%"));
        }
        JPAQuery<UserEntity> where = factory.selectFrom(qUserEntity).where(booleanBuilder);
        long count = where.fetchCount();
        return ResultListImpl.newResult(count, size, page, where
                .limit(size)
                .offset((page - 1) * size)
                .fetchResults()
                .getResults()
                .stream()
                .map(tuple -> UserListDto.builder()
                        .address(tuple.getAddress())
                        .headImg(tuple.getHeadImg())
                        .nickName(tuple.getNickName())
                        .phone(tuple.getPhone())
                        .userName(tuple.getUserName()).build())
                .collect(Collectors.toList()));
    }

    /**
     * 保存或者更新用户信息
     *
     * @param userEntity 用户类
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
