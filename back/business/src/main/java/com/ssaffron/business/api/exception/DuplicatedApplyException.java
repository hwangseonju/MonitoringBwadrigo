package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
public class DuplicatedApplyException extends DuplicateKeyException {
    // PlanService - insertApply : 요금제 신청 시 기존 서비스가 존재하면 값을 넣지 않기 위한 예외 처리

    private ErrorCode errorCode;

    public DuplicatedApplyException(String message){
        super(message);
        log.info("님께서 이미 신청한 서비스가 있습니다.");
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }

}
