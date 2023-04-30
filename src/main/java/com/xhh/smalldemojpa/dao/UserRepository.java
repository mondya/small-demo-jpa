package com.xhh.smalldemojpa.dao;

import com.xhh.smalldemojpa.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NoRepositoryBean用于标识一个接口或者抽象类不应该被自动扫描并创建实例
 */
@Repository
//@NoRepositoryBean
public interface UserRepository extends BaseRepository<User> {
    // @Override
    // getById在测试类中不可用
    // User getById(Long name);
    @Override
    @Query(value = "select * from system_user where id = ?1", nativeQuery = true)
    User getById(Long id);
    
    User findByUserName(String name);
    
    // JPQL方式@Query(value = "select u from User u where (?1 IS NULL OR u.userName LIKE ?1)")
    // 原生sql
    @Query(value = "select * from system_user where (:#{#searchValue} IS NULL OR user_name LIKE :#{#searchValue})", nativeQuery = true)
    Page<User> fetchAllUserBySearch(String searchValue, Pageable pageable); 
}
