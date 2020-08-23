package com.thoughtworks.quiz.common.exception;

import com.thoughtworks.quiz.common.errors.ErrorCode;

public class IllegalParamsException extends BasicException {

    public IllegalParamsException() {
    }

    public IllegalParamsException(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }

    public IllegalParamsException(String errorMessage, ErrorCode errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode.getCode();
    }

}
