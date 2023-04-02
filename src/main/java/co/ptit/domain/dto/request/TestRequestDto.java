package co.ptit.domain.dto.request;

import lombok.*;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestRequestDto {

    private Long id;
    private String code;
    private String name;
    private String userName;
}
