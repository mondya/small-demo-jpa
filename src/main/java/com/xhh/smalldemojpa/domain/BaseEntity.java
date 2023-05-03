package com.xhh.smalldemojpa.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * MappedSupperClass是一个JPA注解，用于标注一个类是映射的超类，它不会被映射到数据库中，但是该类的属性会被子类继承，并映射到子类对应的数据库表中
 * PrePersist注解标识在插入数据之前执行的方法，PreUpdate注解表示在更新数据之前执行的方法。这两个注解的方法必须是无返回值的，并且不能抛出异常
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    
    @Column
    private LocalDateTime dateCreated;
    @Column
    private LocalDateTime lastUpdated;
    @Column
    private Byte status;
    
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.dateCreated = now;
        this.lastUpdated = now;
        this.status = (byte) 1;
    }
    
    @PreUpdate
    public void preUpdated() {
        this.lastUpdated = LocalDateTime.now();
    }
    
}