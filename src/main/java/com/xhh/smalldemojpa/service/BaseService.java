package com.xhh.smalldemojpa.service;

import com.xhh.smalldemojpa.domain.user.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    
    T findById(Long id);
    
    List<T> findByIdInList(List<Long> idList);
    
    void save(T entity);
    
    void update(T entity);
    
    void delete(Long id);
}
