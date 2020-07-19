package com.mine.org.service.account;

import com.common.org.base.BaseService;
import com.common.org.utils.ResultListImpl;
import com.mine.org.dto.account.AccountListDto;
import com.mine.org.entity.QWorkAccountEntity;
import com.mine.org.entity.WorkAccountEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: YU
 * @Date: 10:30 2020/7/16
 * @Description: 账号管理
 */
@Service
public class WorkAccountServiceImpl extends BaseService implements WorkAccountService {

    @Autowired
    private WorkAccountRepository workAccountRepository;

    /* 保存账号 **/
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void saveWorkAccount(WorkAccountEntity entity) {
        workAccountRepository.save(entity);
    }

    /* 删除账号 **/
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteWorkAccount(String id) {
        workAccountRepository.deleteById(id);
    }

    /* 账号列表 **/
    @Override
    public Object getWorkAccountList(int size, int page, String content) {
        QWorkAccountEntity qWorkAccountEntity = QWorkAccountEntity.workAccountEntity;
        // 模糊查询
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!"".equals(content)) { // 是否进行条件查询
            booleanBuilder.and(qWorkAccountEntity.accountTitle.like("%" + content + "%"));
//            booleanBuilder.or(qWorkAccountEntity.accountTitle.like("%" + content + "%"));
//            booleanBuilder.or(qWorkAccountEntity.userName.like("%" + content + "%"));
//            booleanBuilder.or(qWorkAccountEntity.workAccount.like("%" + content + "%"));
        }
        JPAQuery<WorkAccountEntity> where = factory.selectFrom(qWorkAccountEntity)
                .where(booleanBuilder);
        long count = where.fetchCount();
        return ResultListImpl.newResult(count, size, page, where
                .offset((page - 1) * size)
                .limit(size)
                .orderBy(qWorkAccountEntity.createdDate.desc())
                .fetchResults()
                .getResults()
                .stream()
                .map(tuple -> AccountListDto.builder()
                        .id(tuple.getId())
                        .accountName(tuple.getAccountName())
                        .accountTitle(tuple.getAccountTitle())
                        .introduce(tuple.getIntroduce())
                        .userName(tuple.getUserName())
                        .workAccount(tuple.getWorkAccount())
                        .build())
                .collect(Collectors.toList()));
    }


    /* 账号详情 **/
    @Override
    public WorkAccountEntity getWorkAccountDetail(String id) {
        return workAccountRepository.findById(id).orElse(null);
    }
}
