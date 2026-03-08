package com.david.project.service;

import com.david.project.constant.ErrorCode;
import com.david.project.exception.ErrorCodedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    public void throwErrorCodedException(boolean isThrow, ErrorCode errorCode) {
        if (isThrow) {
            throw new ErrorCodedException(errorCode, new String[]{"true", errorCode.name()}, "發生異常: %s".formatted(errorCode.name()));
        } else {
            logger.info("沒有發生異常，isThrow: {}, errorCode: {}", isThrow, errorCode);
        }
    }
}
