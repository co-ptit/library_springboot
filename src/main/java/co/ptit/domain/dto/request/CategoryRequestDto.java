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
public class CategoryRequestDto {

    private String code;
    private String name;
    private String description;
}
