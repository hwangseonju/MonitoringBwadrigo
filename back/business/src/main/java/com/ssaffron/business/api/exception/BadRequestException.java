package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BadRequestException extends RuntimeException{
    public BadRequestException() {
        super("입력값이 잘못됐습니다.");
        log.info("아이디 혹은 비밀번호를 확인 해주세요.");
    }
}
