package co.ptit.domain.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    private LocalTime localTime;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
}
