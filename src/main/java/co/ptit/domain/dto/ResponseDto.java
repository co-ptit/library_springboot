package co.ptit.domain.dto;

import co.ptit.utils.MsgUtil;
import lombok.*;

import java.io.Serializable;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> implements Serializable {

    private boolean success;
    private Data<T> data;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Data<D> implements Serializable {
        String msgContent;
        String msgCode;
        D payload;
    }

    public static <T> ResponseDto<T> ok(String message) {
        return msgExtract(ResponseDto.<T>builder().success(true)
                .data(Data.<T>builder()
                        .msgContent(message)
                        .build())
                .build());
    }

    public static <T> ResponseDto<T> ok(String message, T data) {
        return msgExtract(ResponseDto.<T>builder().success(true)
                .data(Data.<T>builder()
                        .msgContent(message)
                        .payload(data)
                        .build())
                .build());
    }

    public static <T> ResponseDto<T> ok(T data) {
        return ResponseDto.<T>builder().success(true)
                .data(Data.<T>builder()
                        .payload(data)
                        .build())
                .build();
    }

    public static <T> ResponseDto<T> err(String msg) {
        return msgExtract(ResponseDto.<T>builder().success(false)
                .data(Data.<T>builder()
                        .msgContent(msg)
                        .build())
                .build());
    }

    public static <T> ResponseDto<T> err(String key, String msg) {
        return msgExtract(ResponseDto.<T>builder().success(false)
                .data(Data.<T>builder()
                        .msgCode(key)
                        .msgContent(msg)
                        .payload(null)
                        .build())
                .build());
    }

    private static <T> ResponseDto<T> msgExtract(ResponseDto<T> response) {
        String[] extract = response.data.msgContent != null ? response.data.msgContent.split(MsgUtil.SPLIT) : null;
        if (null != extract && extract.length == 2) {
            response.data.msgCode = extract[0];
            response.data.msgContent = extract[1];
        } else if (extract != null && response.getData().getPayload() == null) {
            response.data.payload = (T) response.data.msgContent;
            response.data.msgContent = "";
        }
        return response;
    }

}
