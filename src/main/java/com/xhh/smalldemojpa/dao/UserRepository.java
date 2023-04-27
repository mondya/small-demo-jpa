package com.xhh.smalldemojpa.dao;

import com.xhh.smalldemojpa.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
