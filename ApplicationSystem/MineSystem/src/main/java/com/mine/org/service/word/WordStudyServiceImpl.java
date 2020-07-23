package com.mine.org.service.word;

import com.common.org.base.BaseService;
import com.mine.org.dto.word.WordStudyListDto;
import com.mine.org.entity.QWordStudyEntity;
import com.mine.org.entity.WordExampleEntity;
import com.mine.org.entity.WordPhraseEntity;
import com.mine.org.entity.WordStudyEntity;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: YU
 * @Date: 16:05 2020/7/20
 * @Description: 单词
 */
@Service
public class WordStudyServiceImpl extends BaseService<WordStudyEntity> implements WordStudyService {
    /* 单词 **/
    @Resource
    private WordStudyRepository wordStudyRepository;

    /* 单词例子 **/
    @Resource
    private WordExampleRepository wordExampleRepository;
    /* 单词短语 **/
    @Resource
    private WordPhraseRepository wordPhraseRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public int saveWord(WordStudyEntity wordStudyEntity) {
        QWordStudyEntity qWordStudyEntity = QWordStudyEntity.wordStudyEntity;
        long resultCount = factory.selectFrom(qWordStudyEntity).where(qWordStudyEntity.englishName.eq(wordStudyEntity.getEnglishName())).fetchCount();
        if (resultCount <= 0) {
            WordStudyEntity save = wordStudyRepository.save(wordStudyEntity);
            for (WordExampleEntity entity : wordStudyEntity.getWordExampleEntityList()) {
                entity.setWordStudyEntity(save);
                wordExampleRepository.save(entity);
            }
            for (WordPhraseEntity entity : wordStudyEntity.getWordPhraseEntityList()) {
                entity.setWordStudyEntity(save);
                wordPhraseRepository.save(entity);
            }
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public Object getWordList(int page, int size, String content) {
        QWordStudyEntity qWordStudyEntity = QWordStudyEntity.wordStudyEntity;
        BooleanBuilder condition = new BooleanBuilder();
        if (!StringUtils.isEmpty(content)) {
            condition.and(qWordStudyEntity.chinaName.like("%" + content + "%"));
            condition.or(qWordStudyEntity.englishName.like("%" + content + "%"));
        }
        return Optional.ofNullable(factory.selectFrom(qWordStudyEntity)
                .orderBy(qWordStudyEntity.workState.desc())
                .orderBy(qWordStudyEntity.createdDate.desc())
                .offset((page - 1) * size)
                .limit(size)
                .fetch()).orElse(new ArrayList<>())
                .stream()
                .map(wordStudyEntity -> WordStudyListDto.builder()
                        .id(wordStudyEntity.getId())
                        .chinaName(wordStudyEntity.getChinaName())
                        .englishName(wordStudyEntity.getEnglishName())
                        .wordPronunciation(wordStudyEntity.getWordPronunciation())
                        .workState(wordStudyEntity.getWorkState().getDes()).build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteWord(String id) {
        wordStudyRepository.deleteById(id);
    }

    @Override
    public Object getWordDetail(String id) {
        QWordStudyEntity qWordStudyEntity = QWordStudyEntity.wordStudyEntity;
        factory.selectFrom(qWordStudyEntity);
        return null;
    }
}
