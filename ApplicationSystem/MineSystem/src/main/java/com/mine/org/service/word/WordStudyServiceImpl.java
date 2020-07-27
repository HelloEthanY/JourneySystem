package com.mine.org.service.word;

import com.common.org.base.BaseService;
import com.common.org.utils.ResultListImpl;
import com.mine.org.dto.word.WordStudyDetailDto;
import com.mine.org.dto.word.WordStudyListDto;
import com.mine.org.entity.QWordStudyEntity;
import com.mine.org.entity.WordExampleEntity;
import com.mine.org.entity.WordPhraseEntity;
import com.mine.org.entity.WordStudyEntity;
import com.mine.org.entity.enums.WorkStatusEnum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private WordExampleService wordExampleService;
    /* 单词短语 **/
    @Resource
    private WordPhraseService wordPhraseService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public int saveWord(WordStudyEntity wordStudyEntity) throws Exception {
        QWordStudyEntity qWordStudyEntity = QWordStudyEntity.wordStudyEntity;
        if (StringUtils.isEmpty(wordStudyEntity.getId())) {
            long resultCount = factory.selectFrom(qWordStudyEntity).where(qWordStudyEntity.englishName.eq(wordStudyEntity.getEnglishName())).fetchCount();
            if (resultCount > 0) {
                return 1;
            }
        }
        WordStudyEntity save = wordStudyRepository.save(wordStudyEntity);
        for (WordExampleEntity entity : wordStudyEntity.getWordExampleEntityList()) {
            entity.setWordStudyEntity(save);
            wordExampleService.saveWordExample(entity);
        }
        for (WordPhraseEntity entity : wordStudyEntity.getWordPhraseEntityList()) {
            entity.setWordStudyEntity(save);
            wordPhraseService.savePhraseEntity(entity);
        }
        return 0;
    }

    @Override
    public Object getWordList(int page, int size, String content, WorkStatusEnum state) {
        QWordStudyEntity qWordStudyEntity = QWordStudyEntity.wordStudyEntity;
        BooleanBuilder condition = new BooleanBuilder();
        if (!StringUtils.isEmpty(content)) {
            condition.and(qWordStudyEntity.chinaName.like("%" + content + "%"));
            condition.or(qWordStudyEntity.englishName.like("%" + content + "%"));
        }
        // System.out.println("state::" + state);
       /* if (!Objects.isNull(state)) {
            condition.and(qWordStudyEntity.workState.eq(state));
        }*/
        JPAQuery<WordStudyEntity> where = factory.selectFrom(qWordStudyEntity).where(condition);
        return ResultListImpl.newResult(where.fetchCount(), size, page,
                Optional.ofNullable(where
                        .offset((page - 1) * size)
                        .limit(size)
                        .orderBy(qWordStudyEntity.createdDate.desc())
                        .fetch()).orElse(new ArrayList<>())
                        .stream()
                        .map(wordStudyEntity -> WordStudyListDto.builder()
                                .id(wordStudyEntity.getId())
                                .chinaName(wordStudyEntity.getChinaName())
                                .englishName(wordStudyEntity.getEnglishName())
                                .wordPronunciation(wordStudyEntity.getWordPronunciation())
                                .studyState(wordStudyEntity.getWorkState())
                                .build())
                        .collect(Collectors.toList()));
    }

    @Override
    public void deleteWord(String id) {
        wordStudyRepository.deleteById(id);
    }

    @Override
    public Object getWordDetail(String id) throws Exception {
        QWordStudyEntity qWordStudyEntity = QWordStudyEntity.wordStudyEntity;
        WordStudyEntity wordStudyEntity = factory.selectFrom(qWordStudyEntity)
                .where(qWordStudyEntity.id.eq(id))
                .fetchFirst();
        if (wordStudyEntity == null)
            return null;
        return WordStudyDetailDto.builder()
                .id(wordStudyEntity.getId())
                .chinaName(wordStudyEntity.getChinaName())
                .englishName(wordStudyEntity.getEnglishName())
                .studyState(wordStudyEntity.getWorkState().getDes())
                .wordPronunciation(wordStudyEntity.getWordPronunciation())
                .wordExampleEntityList(wordExampleService.getWordExampleList(id))
                .wordPhraseEntityList(wordPhraseService.getWordPhraseList(id))
                .build();
    }
}
