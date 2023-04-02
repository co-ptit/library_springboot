package co.ptit.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestResponseDto {

    private Long id;
    private String code;
    private String name;
    private Integer status;
    private String createUser;
    private LocalDateTime createDatetime;
    private String updateUser;
    private LocalDateTime updateDatetime;
}
