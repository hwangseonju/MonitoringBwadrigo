package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistedApplyException extends RuntimeException{
    public ExistedApplyException(String memberName){
        super(memberName + "님께서 기존에 신청한 서비스와 동일합니다.");
        log.info("{}님께서 기존에 신청한 서비스와 동일합니다.", memberName);
    }
}
