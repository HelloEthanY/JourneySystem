package com.mine.org.service.word;

import com.common.org.base.BaseService;
import com.mine.org.dto.word.WordExampleListDto;
import com.mine.org.entity.QWordExampleEntity;
import com.mine.org.entity.WordExampleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: YU
 * @Date: 10:57 2020/7/24
 * @Description: 单词操作
 */
@Service
public class WordExampleServiceImpl extends BaseService implements WordExampleService {

    @Resource
    private WordExampleRepository wordExampleRepository;

    @Override
    public List<WordExampleListDto> getWordExampleList(String wordId) {
        QWordExampleEntity qWordExampleEntity = QWordExampleEntity.wordExampleEntity;
        return factory.selectFrom(qWordExampleEntity)
                .where(qWordExampleEntity.wordStudyEntity.id.eq(wordId))
                .fetch()
                .stream()
                .map(wordExampleEntity -> WordExampleListDto.builder()
                        .chinaName(wordExampleEntity.getChinaName())
                        .englishName(wordExampleEntity.getEnglishName())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveWordExample(WordExampleEntity entity) throws Exception {
        wordExampleRepository.save(entity);
    }
}
