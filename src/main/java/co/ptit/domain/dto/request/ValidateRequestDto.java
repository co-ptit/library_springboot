package co.ptit.domain.dto.request;

import co.ptit.domain.common.StripString;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.constraints.NotBlank;
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

    Long id;

    @NotBlank(message = "name.required")
    @JsonDeserialize(using = StripString.class)
    String name;

    String code;

}
