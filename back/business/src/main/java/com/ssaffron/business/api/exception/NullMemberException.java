package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NullMemberException extends NullPointerException{
    public NullMemberException(){
        super("존재하지 않은 사용자입니다.");
        log.info("존재하지 않은 사용자입니다.");
    }
}
