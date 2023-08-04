package co.ptit.domain.dto.response;

import lombok.*;

import java.time.LocalDate;

/**
 * project: library_springboot
 * author:  HieuDM
 * date:    8/4/2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersResponseDto {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private String userName;
    private LocalDate createDate;

}
