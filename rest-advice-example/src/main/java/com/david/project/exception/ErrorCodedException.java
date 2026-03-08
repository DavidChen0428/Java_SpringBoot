package com.david.project.exception;

import com.david.project.constant.ErrorCode;

import java.util.Objects;

public class ErrorCodedException extends RuntimeException {

    private final ErrorCode errorCode;

    private final String[] parameters;

    public ErrorCodedException(ErrorCode errorCode) {
        this(errorCode, null, null, null);
    }

    public ErrorCodedException(ErrorCode errorCode, String[] parameters) {
        this(errorCode, parameters, null, null);
    }

    public ErrorCodedException(ErrorCode errorCode, Throwable cause) {
        this(errorCode, null, null, cause);
    }

    public ErrorCodedException(ErrorCode errorCode, String message) {
        this(errorCode, null, message, null);
    }

    public ErrorCodedException(ErrorCode errorCode, String[] parameters, String message) {
        this(errorCode, parameters, message, null);
    }

    public ErrorCodedException(ErrorCode errorCode, String message, Throwable cause) {
        this(errorCode, null, message, cause);
    }

    public ErrorCodedException(ErrorCode errorCode, String[] parameters, String message, Throwable cause) {
        super(Objects.requireNonNullElse(message, errorCode.getDescription()), cause);
        this.errorCode = errorCode;
        this.parameters = parameters == null ? new String[0] : parameters;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String[] getParameters() {
        return parameters;
    }
}
