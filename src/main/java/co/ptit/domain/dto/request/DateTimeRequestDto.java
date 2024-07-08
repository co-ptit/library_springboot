package co.ptit.domain.dto.request;

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

    private LocalDate localDate;
}
