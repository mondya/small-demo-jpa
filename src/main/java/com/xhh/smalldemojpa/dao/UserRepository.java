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
    @Query(value = "select * from system_user where id = :id", nativeQuery = true)
    User getById(Long id);
    
    User findByUserName(String name);
    
    // :#{#searchValue}和#{#{searchValue}}的区别：:#{#searchValue}是一种SpEL表达式，它可以在运行时动态计算出占位符的位置，并将其绑定到查询参数中。
    // 而 #{#{searchValue}}是jpa中的一种占位符，它会在运行时将占位符替换成实际的参数值，并通过JPA的参数绑定机制将参数绑定到查询语句中。
    // 所以 (:#{#searchValue} IS NULL OR u.userName LIKE :#{#searchValue}) 如果没有加上 :，在执行sql时会报错 unexpected char # ....
    // JPQL方式
    @Query(value = "select u from User u where (:#{#searchValue} IS NULL OR u.userName LIKE :#{#searchValue})")
    //@Query(value = "select u from User u where (?1 IS NULL OR u.userName LIKE ?1)")
    // 原生sql
//    @Query(value = "select * from system_user where (:#{#searchValue} = '' OR user_name LIKE :#{#searchValue})", nativeQuery = true)
    Page<User> fetchAllUserBySearch(String searchValue, Pageable pageable); 
}
