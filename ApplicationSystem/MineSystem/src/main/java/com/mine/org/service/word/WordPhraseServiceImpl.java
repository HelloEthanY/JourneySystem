package com.mine.org.service.word;

import com.common.org.base.BaseService;
import com.mine.org.dto.word.WordPhraseListDto;
import com.mine.org.entity.QWordPhraseEntity;
import com.mine.org.entity.WordPhraseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: YU
 * @Date: 11:07 2020/7/24
 * @Description: 单词短语
 */
@Service
public class WordPhraseServiceImpl extends BaseService implements WordPhraseService {

    @Resource
    private WordPhraseRepository wordPhraseRepository;

    @Override
    public List<WordPhraseListDto> getWordPhraseList(String wordId) {
        QWordPhraseEntity qWordPhraseEntity = QWordPhraseEntity.wordPhraseEntity;
        return factory.selectFrom(qWordPhraseEntity)
                .where(qWordPhraseEntity.wordStudyEntity.id.eq(wordId))
                .fetch()
                .stream()
                .map(wordPhraseEntity -> WordPhraseListDto.builder()
                        .chinaName(wordPhraseEntity.getChinaName())
                        .englishName(wordPhraseEntity.getEnglishName())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void savePhraseEntity(WordPhraseEntity entity) {
        wordPhraseRepository.save(entity);
    }
}
