package com.xhh.smalldemojpa.service;

import com.xhh.smalldemojpa.domain.user.BaseEntity;

public interface BaseService<T extends BaseEntity> {
    
    T findById(Long id);
    
    void save(T entity);
    
    void update(T entity);
    
    void delete(Long id);
}
