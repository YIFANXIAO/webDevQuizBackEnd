package com.thoughtworks.quiz.common.interceptor;

import com.thoughtworks.quiz.common.errors.ErrorMessage;
import com.thoughtworks.quiz.common.exception.NoDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<ErrorMessage> handleOrderControllerException(NoDataException ex) {
        ErrorMessage errorMessage = ex.getErrorMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
