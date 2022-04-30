package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DuplicatedEmailException extends NullPointerException{
    public DuplicatedEmailException(String memberEmail){
        super("이미 존재하는 이메일: "+memberEmail);
        log.info("이미 존재하는 이메일: {}", memberEmail);
    }
}
