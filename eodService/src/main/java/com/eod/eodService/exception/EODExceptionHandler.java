package com.eod.eodService.exception;

import com.eod.eodService.data.CommonExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EODExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<Object> commonException(CommonException e) {
        String error_message = null;
        if (e.getMessage() == null) {
            error_message = "Network error.";
        } else {
            error_message = e.getMessage();
        }
        CommonExceptionModel customException = new CommonExceptionModel(
                e.getStatusCode().value(),
                e.getMessage()
        );
        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }
}
