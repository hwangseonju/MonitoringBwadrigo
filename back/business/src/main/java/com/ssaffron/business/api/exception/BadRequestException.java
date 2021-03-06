package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BadRequestException extends RuntimeException{
    public BadRequestException() {
        super("입력값이 잘못됐습니다.");
    }
}
