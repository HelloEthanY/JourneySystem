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
 * @Date: 11:10 2020/7/19
 * @Description: 权限表
 */
@Entity(name = "mine_role")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleEntity extends BaseEntity {

    /* 权限等级 **/
    @Column(name = "level", length = 20)
    private String level;

}
