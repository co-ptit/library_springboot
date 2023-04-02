package co.ptit.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * project: library_springboot
 * date:    4/1/2023
 */

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "CREATE_USER")
    protected String createUser;

    @Basic
    @Column(name = "CREATE_DATETIME")
    protected LocalDateTime createDatetime;

    @Basic
    @Column(name = "UPDATE_USER")
    protected String updateUser;

    @Basic
    @Column(name = "UPDATE_DATETIME")
    protected LocalDateTime updateDatetime;
}
