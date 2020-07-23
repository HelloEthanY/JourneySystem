package com.common.org.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements BaseID {

    @ApiModelProperty(value = "ID", example = "1001")
    @Id
    @GenericGenerator(name = "myId", strategy = "com.common.org.interceptor.AutoIdGenerator")
    @GeneratedValue(generator = "myId")
    @Column(name = "id", length = 50, nullable = false, unique = true) // 设置其长度为 50
    private String id;

}
