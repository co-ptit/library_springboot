package co.ptit.domain.entity;

import co.ptit.utils.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = Constant.Sequence.SEQ_USERS, sequenceName = Constant.Sequence.SEQ_USERS, allocationSize = 1)
    @Column(name = "USER_ID")
    private Long userId;

    @Basic
    @Column(name = "USER_NAME")
    private String userName;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Basic
    @Column(name = "ROLE")
    private String role;

    @Basic
    @Column(name = "USER_INFO_ID")
    private Long userInfoId;

    @Basic
    @Column(name = "STATUS")
    protected Integer status;
}
