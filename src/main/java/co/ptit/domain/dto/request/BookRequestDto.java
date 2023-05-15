package co.ptit.domain.dto.request;

import lombok.*;

import java.time.LocalDate;

/**
 * project: library_springboot
 * date:    5/13/2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDto {

    private Long bookId;
    private String title;
    private String author;
    private Long categoryId;
    private LocalDate releaseDate;
    private Long price;
    private Integer pageNumber;
    private Long quantitySold;
    private String description;
    private String coverImageUrl;
}
