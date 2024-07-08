package co.ptit.domain.dto.request;

import co.ptit.domain.common.ValidateDate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.time.LocalDate;

/**
 * @author: HieuDo
 * @since: 1/28/2024
 * @project: library_springboot
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateTimeRequestDto {

    @JsonDeserialize(using = ValidateDate.class)
    private LocalDate localDate;
}
