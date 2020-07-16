package com.course.org.entity;

import com.common.org.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "student_entity")
public class StudentEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

}
