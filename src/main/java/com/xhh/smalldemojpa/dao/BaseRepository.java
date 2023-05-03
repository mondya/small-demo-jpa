package com.xhh.smalldemojpa.dao;

import com.xhh.smalldemojpa.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
    
    @Query("update #{#entityName} e set e.status = 0, e.lastUpdated = NOW() where e.id = ?1")
    @Modifying
    @Override
    void deleteById(Long id);
    
    @Query("select e from #{#entityName} e where e.id in :idList")
    List<T> findByIdList(List<Long> idList);
    
}  
