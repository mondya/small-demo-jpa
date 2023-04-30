package com.xhh.smalldemojpa.dao;

import com.xhh.smalldemojpa.domain.user.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
    
    @Query("update #{#entityName} e set e.status = 0, e.lastUpdated = NOW() where e.id = ?1")
    @Modifying
    @Override
    void deleteById(Long id);
    
}  