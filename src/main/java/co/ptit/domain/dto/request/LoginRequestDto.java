package co.ptit.domain.dto.request;

import lombok.*;

/**
 * project: library_springboot
 * date:    4/17/2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {

    private String userName;
    private String password;

}
