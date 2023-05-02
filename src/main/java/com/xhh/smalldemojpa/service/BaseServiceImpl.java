package com.xhh.smalldemojpa.service;

import com.xhh.smalldemojpa.dao.BaseRepository;
import com.xhh.smalldemojpa.domain.user.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BaseServiceImpl<R extends BaseRepository<T>, T extends BaseEntity> implements BaseService<T>{
    
    @Resource 
    private R repository;
    
    @Override
    public T findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> findByIdInList(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            log.info("[BaseServiceImpl] idList is null");
            return new ArrayList<T>();
        }
        return repository.findByIdList(idList);
    }

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public void update(T entity) {
        repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
