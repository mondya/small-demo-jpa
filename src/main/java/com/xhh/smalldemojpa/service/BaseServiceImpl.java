package com.xhh.smalldemojpa.service;

import com.xhh.smalldemojpa.dao.BaseRepository;
import com.xhh.smalldemojpa.domain.user.BaseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaseServiceImpl<R extends BaseRepository<T>, T extends BaseEntity> implements BaseService<T>{
    
    @Resource 
    private R repository;
    
    @Override
    public T findById(Long id) {
        return repository.findById(id).orElse(null);
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
