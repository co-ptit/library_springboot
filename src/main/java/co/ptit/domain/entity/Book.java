package co.ptit.domain.entity;

import co.ptit.utils.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

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
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = Constant.Sequence.SEQ_BOOK, sequenceName = Constant.Sequence.SEQ_BOOK, allocationSize = 1)
    @Column(name = "BOOK_ID")
    private Long bookId;
    
    @Basic
    @Column(name = "TITLE")
    private String title;
    
    @Basic
    @Column(name = "AUTHOR")
    private String author;
    
    @Basic
    @Column(name = "CATEGORY_ID")
    private Long categoryId;
    
    @Basic
    @Column(name = "RELEASE_DATE")
    private LocalDateTime releaseDate;
    
    @Basic
    @Column(name = "PRICE")
    private Long price;
    
    @Basic
    @Column(name = "PAGE_NUMBER")
    private Integer pageNumber;
    
    @Basic
    @Column(name = "QUANTITY_SOlD")
    private Integer quantitySOlD;
    
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Basic
    @Column(name = "COVER_IMAGE_URL")
    private String coverImageUrl;

    @Basic
    @Column(name = "STATUS")
    protected Integer status;
}
