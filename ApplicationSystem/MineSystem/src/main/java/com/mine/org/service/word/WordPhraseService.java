package com.mine.org.service.word;

import com.mine.org.dto.word.WordPhraseListDto;
import com.mine.org.entity.WordPhraseEntity;

import java.util.List;

/**
 * @Author: YU
 * @Date: 11:03 2020/7/24
 * @Description: 单词短语
 */
public interface WordPhraseService {

    /* 获取单词短语列表 **/
    List<WordPhraseListDto> getWordPhraseList(String wordId) throws Exception;

    /* 保存单词短语 **/
    void savePhraseEntity(WordPhraseEntity entity) throws Exception;
}
