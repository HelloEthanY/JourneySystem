package com.mine.org.service.word;

import com.mine.org.dto.word.WordExampleListDto;
import com.mine.org.entity.WordExampleEntity;

import java.util.List;

/**
 * @Author: YU
 * @Date: 10:56 2020/7/24
 * @Description: 单词例子
 */

public interface WordExampleService {

    /* 获取单词的例子 **/
    List<WordExampleListDto> getWordExampleList(String wordId) throws Exception;

    /* 保存单词例子**/
    void saveWordExample(WordExampleEntity entity) throws Exception;


}
