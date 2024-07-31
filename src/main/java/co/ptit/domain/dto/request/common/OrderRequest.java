package co.ptit.domain.dto.request.common;

import co.ptit.domain.common.StripString;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author: HieuDo
 * @since: 7/30/2024
 * @project: library_springboot
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @JsonDeserialize(using = StripString.class)
    @NotBlank(message = "key.sort.required")
    String keySort;

    @JsonDeserialize(using = StripString.class)
    String orderBy;

}
