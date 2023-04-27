package com.xhh.smalldemojpa.domain.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * MappedSupperClass是一个JPA注解，用于标注一个类是映射的超类，它不会被映射到数据库中，但是该类的属性会被子类继承，并映射到子类对应的数据库表中
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    
    @Column
    private LocalDateTime dataCreated;
    @Column
    private LocalDateTime lastUpdated;
    
    
}