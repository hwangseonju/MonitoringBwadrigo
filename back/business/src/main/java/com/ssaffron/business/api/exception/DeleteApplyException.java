package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteApplyException extends RuntimeException {
    public DeleteApplyException(String memberName){
        super(memberName + "님께서 신청한 요금제가 없습니다.");
        log.info("{}님께서 신청한 서비스가 없습니다.", memberName);
    }
}
