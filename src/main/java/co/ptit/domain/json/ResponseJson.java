package co.ptit.domain.json;

import lombok.*;

import java.io.Serializable;

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
public class ResponseJson implements Serializable {

    boolean success;
    Data data;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data implements Serializable {
        String msg_content;
        String msg_code;
        PayloadJson payload;
    }
}
