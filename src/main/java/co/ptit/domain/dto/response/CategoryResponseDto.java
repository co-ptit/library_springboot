package co.ptit.domain.dto.response;

import lombok.*;

/**
 * project: library_springboot
 * date:    5/13/2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {

    private String code;
    private String name;
    private String description;
}
