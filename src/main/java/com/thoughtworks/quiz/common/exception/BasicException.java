package com.thoughtworks.quiz.common.exception;

import com.thoughtworks.quiz.common.errors.ErrorCode;
import com.thoughtworks.quiz.common.errors.ErrorMessage;

public class BasicException extends RuntimeException{

    public String errorMessage = ErrorCode.BASIC_ERROR.getMessage();

    public int errorCode = ErrorCode.BASIC_ERROR.getCode();

    public ErrorMessage getErrorMessage() {
        return new ErrorMessage(errorCode, errorMessage);
    }
}
