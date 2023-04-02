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
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = Constant.Sequence.SEQ_CATEGORY, sequenceName = Constant.Sequence.SEQ_CATEGORY, allocationSize = 1)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Basic
    @Column(name = "CODE")
    private String code;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "STATUS")
    protected Integer status;
}
