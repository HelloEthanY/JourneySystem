package com.mine.org.entity;

import com.common.org.base.BaseAuditorEntity;
import com.mine.org.entity.enums.TechnologyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Author: YU
 * @Date: 9:41 2020/7/16
 * @Description: 博客收藏
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "work_blog_collection")
public class BlogCollectionEntity extends BaseAuditorEntity {

    /* 博客地址 **/
    @Column(name = "blog_address")
    private String blogAddress;

    /* 博客简介 **/
    @Column(name = "introduce")
    private String introduce;

    /* 博客时间 **/
    @Column(name = "blog_time")
    private String blogTime;

    /* 博客名字 **/
    @Column(name = "blog_name")
    private String blogName;

    /* 博客类型 **/
    @Column(name = "blog_type", length = 10)
    private TechnologyTypeEnum blogType;

}
