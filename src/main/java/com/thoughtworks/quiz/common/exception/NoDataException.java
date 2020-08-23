package com.thoughtworks.quiz.common.exception;

import com.thoughtworks.quiz.common.errors.ErrorCode;
import com.thoughtworks.quiz.common.errors.ErrorMessage;

public class NoDataException extends BasicException {

    public NoDataException(ErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorMessage;
    }

    public NoDataException(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }

    public NoDataException() {

    }

}
