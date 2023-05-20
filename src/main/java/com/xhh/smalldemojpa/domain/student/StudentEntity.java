package com.xhh.smalldemojpa.domain.student;

import com.xhh.smalldemojpa.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Table(name = "system_student")
@Entity
@Data
@ToString(callSuper = true)
public class StudentEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    @Column
    String name;
    
    @Column
    String code;
    
    @Column
    String idCard;
    
    @Column
    String homePlace;

    /**
     * 包装类型 Boolean isNormal字段生成的方法为getIsNormal()和setIsNormal()
     * 基本类型 boolean isNormal和normal字段生成的方法都是为isNormal()和setNormal()
     * 需要注意在某些情况下，可能会造成属性读取为空的情况
     */
    @Column
    Boolean isNormal;
    
}
