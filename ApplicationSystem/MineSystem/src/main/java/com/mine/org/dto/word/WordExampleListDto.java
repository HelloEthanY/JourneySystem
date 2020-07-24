package com.mine.org.dto.word;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: YU
 * @Date: 16:10 2020/7/23
 * @Description: 单词列表Dto
 */
@ApiModel(description = "单词例子列表数据")
@Builder
@Data
public class WordExampleListDto {

    /* 英文名称 unique 唯一性**/
    @ApiModelProperty(value = "英文名字", position = 1, example = "hello")
    private String englishName;

    /* 中文名称 **/
    @ApiModelProperty(value = "中文名称", position = 1, example = "你好")
    private String chinaName;

}
