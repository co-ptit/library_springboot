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

    private String title;
    private String author;
    private String categoryId;
    private LocalDate releaseDate;
    private Long price;
    private Long pageNumber;
    private Long quantitySold;
    private String description;
    private String coverImageUrl;
}
