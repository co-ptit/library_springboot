package co.ptit.domain.dto.request.common;

import co.ptit.utils.Constant;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import java.util.List;

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
public class PagingRequest {

    Integer pageIndex = Constant.PAGE_INDEX_DEFAULT;

    Integer pageSize = Constant.PAGE_SIZE_DEFAULT;

    @Valid
    List<OrderRequest> orderList;

}
