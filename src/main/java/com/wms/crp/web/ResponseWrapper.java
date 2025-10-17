package com.wms.crp.web;

import lombok.*;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ResponseWrapper<T> {
    private boolean success;
    private T data;
    private List<String> messages;
    private List<String> messageCodes;
}
