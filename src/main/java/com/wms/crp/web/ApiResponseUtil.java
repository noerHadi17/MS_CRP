package com.wms.crp.web;

import java.util.List;

public final class ApiResponseUtil {
    private ApiResponseUtil() {}
    public static <T> ResponseWrapper<T> success(T data, String code, String msg) {
        return ResponseWrapper.<T>builder().success(true).data(data)
                .messageCodes(code != null ? List.of(code) : null)
                .messages(msg != null ? List.of(msg) : null).build();
    }
    public static ResponseWrapper<Void> error(String code, String msg) {
        return ResponseWrapper.<Void>builder().success(false)
                .messageCodes(code != null ? List.of(code) : null)
                .messages(msg != null ? List.of(msg) : null)
                .build();
    }
    public static ResponseWrapper<Void> validationMessages(List<String> messages) {
        return ResponseWrapper.<Void>builder().success(false).messages(messages).build();
    }
}
