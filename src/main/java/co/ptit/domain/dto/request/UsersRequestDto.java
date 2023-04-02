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
public class UsersRequestDto {

    private String userName;
    private String password;

    //TODO: ...
}
