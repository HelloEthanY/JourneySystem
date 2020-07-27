package com.mine.org.dto.word;

import com.mine.org.entity.enums.WorkStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: YU
 * @Date: 16:10 2020/7/23
 * @Description: 单词列表Dto
 */
@ApiModel(description = "单词列表数据")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordStudyListDto {
    /* 英文id**/
    @ApiModelProperty(value = "ID", position = 1, example = "xddswewa25545333322")
    private String id;

    /* 英文名称 unique 唯一性**/
    @ApiModelProperty(value = "英文名字", position = 1, example = "hello")
    private String englishName;

    /* 中文名称 **/
    @ApiModelProperty(value = "中文名称", position = 1, example = "你好")
    private String chinaName;

    /* 发音名称 **/
    @ApiModelProperty(value = "发音名称", position = 1, example = "")
    private String wordPronunciation;

    /* 学习状态 **/
    @ApiModelProperty(value = "学习状态", position = 1, example = "")
    private WorkStatusEnum studyState;

}
