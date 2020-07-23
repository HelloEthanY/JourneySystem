package com.mine.org.entity;

import com.common.org.base.BaseAuditorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @Author: YU
 * @Date: 14:56 2020/7/20
 * @Description: 单词例子类
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "word_example")
public class WordExampleEntity extends BaseAuditorEntity {

    /* 英文名称 **/
    @Column(name = "english_name", length = 100)
    private String englishName;

    /* 中文名称 **/
    @Column(name = "china_name", length = 100)
    private String chinaName;

    /* 单词 ：可选属性optional=false,表示 单词 不能为空。删除例子，不影响单词 **/
    @ManyToOne(targetEntity = WordStudyEntity.class, optional = false)
    @JoinColumn(name = "word_study_id")
    private WordStudyEntity wordStudyEntity;

}
