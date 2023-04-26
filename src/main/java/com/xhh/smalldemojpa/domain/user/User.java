package com.xhh.smalldemojpa.domain.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_user")
@Data
public class User extends BaseEntiry implements Serializable {
    
    @Id
    private Long id;
    
    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private Byte gender;

    @Column
    private String idCard;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private LocalDateTime birthday;

    @Column
    private Byte status;

}
