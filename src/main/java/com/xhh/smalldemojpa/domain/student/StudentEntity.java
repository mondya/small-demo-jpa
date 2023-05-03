package com.xhh.smalldemojpa.domain.student;

import com.xhh.smalldemojpa.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    
    
}
