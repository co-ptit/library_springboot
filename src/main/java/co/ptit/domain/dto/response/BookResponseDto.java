package co.ptit.domain.dto.response;

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
public class BookResponseDto {

    private String title;
    private String author;
    private String description;
    private LocalDate releaseDate;
    private Integer pageNumber;
    private String categoryName;
}
