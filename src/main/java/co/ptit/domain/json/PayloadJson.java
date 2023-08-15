package co.ptit.domain.json;

import lombok.*;

import java.util.List;

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
public class PayloadJson {

    PageJson page;
    List<UserJson> data;
}
