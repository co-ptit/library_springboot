package co.ptit.domain.json;

import lombok.*;

/**
 * project: library_springboot
 * author:  HieuDM
 * date:    8/15/2023
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageJson {

    Integer pageIndex;
    Integer pageSize;
    Integer total;
}
