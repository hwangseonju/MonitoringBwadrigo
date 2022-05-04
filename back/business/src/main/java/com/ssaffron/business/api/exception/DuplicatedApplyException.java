package com.ssaffron.business.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;

@Slf4j
public class DuplicatedApplyException extends DuplicateKeyException {
    public DuplicatedApplyException(String memberName){
        super(memberName + "님께서 이미 신청한 서비스가 있습니다.");
        log.info("{}님께서 이미 신청한 서비스가 있습니다.", memberName);
    }
}
