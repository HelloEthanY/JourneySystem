package com.common.org.base;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;

/**
 * @author 小妖
 */
public abstract class BaseService<T> {

    @Autowired
    protected EntityManager entityManager;

    protected JPAQueryFactory factory;

    @Lazy
    @Resource
    protected BaseRepository<T> repository;

    @PostConstruct // 指的是在项目启动的时候执行这个方法
    public void initJpaQueryFactory() {
        factory = new JPAQueryFactory(entityManager);
    }

}
