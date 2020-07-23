package com.mine.org.service.word;

import com.mine.org.entity.WordStudyEntity;
/**
  *@Author: YU
  *@Date: 16:30 2020/7/20
  *@Description: 单词
  */
public interface WordStudyService {

    /* 保存单词 **/
    int saveWord(WordStudyEntity wordStudyEntity) throws Exception;

    /* 单词列表 **/
    Object getWordList(int page, int size, String content) throws Exception;

    /* 删除单词 **/
    void deleteWord(String id) throws Exception;

    /* 单词详情 **/
    Object getWordDetail(String id) throws Exception;


}
