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
public class Test extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = Constant.Sequence.SEQ_TEST, sequenceName = Constant.Sequence.SEQ_BOOK, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Basic
    @Column(name = "CODE")
    private String code;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "STATUS")
    protected Integer status;
}
