package co.ptit.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author: HieuDo
 * @since: 1/31/2024
 * @project: library_springboot
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ValidateRequestDto {

    @NotNull(message = "name is required")
    String name;

    String code;

}
