package com.example.student.affairs.common.exception;

/**
 * 自定义业务异常
 */
public class BusinessException extends RuntimeException {
    private Integer errorCode;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
