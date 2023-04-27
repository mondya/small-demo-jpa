package com.xhh.smalldemojpa.domain.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * EqualsAndHashCode是一个用于在Java类中生成equals和hashCode方法的注解，这些方法默认会比较对象的所有属性。通过设置callSuper = true设置让生成的方法也调用父类的方法
 * /@Data: @ToString, @EqualsAndHashCode, @Getter, @Setter 和 @RequiredArgsConstructor 注解的联合使用
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_user")
@Data
//@ToString(callSuper = true)
public class User extends BaseEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                ", status=" + status +
                '}' + super.toString();
    }
}
