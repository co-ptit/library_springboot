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
public class RegisterRequestDto {

    private String userName;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;

}
