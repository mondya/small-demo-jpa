package com.xhh.smalldemojpa.service;

import com.xhh.smalldemojpa.domain.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    
    T findById(Long id);
    
    List<T> findByIdInList(List<Long> idList);
    
    void save(T entity);
    
    void update(T entity);
    
    void delete(Long id);
    
    List<T> findAll();
    
    void saveAll(List<T> entityList);
}
