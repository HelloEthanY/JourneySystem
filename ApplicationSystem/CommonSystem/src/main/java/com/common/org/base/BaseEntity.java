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
    @GenericGenerator(name = "myId", strategy = "com.research.org.app.interceptor.AutoIdGenerator")
    @GeneratedValue(generator = "myId")
 /*   @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")*/
    @Column(name = "id", length = 50, nullable = false) // 设置其长度为 50
    private String id;

}
