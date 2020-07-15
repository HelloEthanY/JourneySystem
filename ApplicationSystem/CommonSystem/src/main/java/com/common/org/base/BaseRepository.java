package com.common.org.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ming zhu
 * @version 1.0
 * @date 2020/4/8 13:18
 * @description 1.0
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, String>, QuerydslPredicateExecutor<T>, JpaSpecificationExecutor<T> {

}


