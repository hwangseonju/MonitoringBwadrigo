package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;

@Slf4j
public class NoSuchApplyForException extends NullPointerException {
    public NoSuchApplyForException(String memberName){
        super(memberName + "님께서 신청한 서비스가 없습니다.");
        log.info("{}님께서 신청한 서비스가 없습니다.", memberName);
    }
}