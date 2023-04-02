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
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = Constant.Sequence.SEQ_COMMENT, sequenceName = Constant.Sequence.SEQ_COMMENT, allocationSize = 1)
    @Column(name = "COMMENT_ID")
    private Long commentId;

    @Basic
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Basic
    @Column(name = "USER_ID")
    private Long userId;

    @Basic
    @Column(name = "CONTENT")
    private String content;

    @Basic
    @Column(name = "RATING")
    private Integer rating;

    @Basic
    @Column(name = "STATUS")
    protected Integer status;
}
