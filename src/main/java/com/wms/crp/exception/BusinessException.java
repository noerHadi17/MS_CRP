package com.wms.crp.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String messageCode;

    public BusinessException(String messageCode) { super(messageCode); this.messageCode = messageCode; }
}

