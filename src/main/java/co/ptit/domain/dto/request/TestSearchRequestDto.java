package co.ptit.domain.dto.request;

import co.ptit.domain.dto.request.common.PagingRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author: HieuDo
 * @since: 7/30/2024
 * @project: library_springboot
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TestSearchRequestDto extends PagingRequest {

    String name;
    String code;

}
