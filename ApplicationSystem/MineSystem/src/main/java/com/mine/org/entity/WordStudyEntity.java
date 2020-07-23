package com.mine.org.entity;

import com.common.org.base.BaseAuditorEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mine.org.entity.enums.WorkStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @Author: YU
 * @Date: 14:48 2020/7/20
 * @Description: 学习单词的表
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "word_study")
public class WordStudyEntity extends BaseAuditorEntity {

    /* 英文名称 unique 唯一性**/
    @Column(name = "english_name", length = 50, unique = true)
    private String englishName;

    /* 中文名称 **/
    @Column(name = "china_name", length = 100)
    private String chinaName;

    /* 发音名称 **/
    @Column(name = "word_pronunciation", length = 50)
    private String wordPronunciation;

    /* 学习状态 **/
    @Column(name = "study_state", length = 20)
    private WorkStatusEnum workState;

    /* 例子 **/
    @OneToMany()
    @JoinColumn
    @JsonBackReference
    private Set<WordExampleEntity> wordExampleEntityList;

    /* 短语 **/
    @OneToMany()
    @JoinColumn
    @JsonBackReference
    private Set<WordPhraseEntity> wordPhraseEntityList;
}
