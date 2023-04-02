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
public class UserInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = Constant.Sequence.SEQ_USER_INFO, sequenceName = Constant.Sequence.SEQ_USER_INFO, allocationSize = 1)
    @Column(name = "USER_INFO_ID")
    private Long userInfoId;

    @Basic
    @Column(name = "FULL_NAME")
    private String fullName;

    @Basic
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Basic
    @Column(name = "EMAIL")
    private String email;

    @Basic
    @Column(name = "ADDRESS")
    private String address;

    @Basic
    @Column(name = "AVATAR_URL")
    private String avatarUrl;

    @Basic
    @Column(name = "STATUS")
    protected Integer status;
}
