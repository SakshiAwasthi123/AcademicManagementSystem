package com.company.ams.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(AMSException.class)
    public ResponseEntity<ErrorResponse> handleAMSException(AMSException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        String message = ex.getDetailMessage() != null ? ex.getDetailMessage() : errorCode.getError();

        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.getStatus().value(),
                message
        );
        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value(),
                "Something went wrong"
        );
        return new ResponseEntity<>(errorResponse, ErrorCode.INTERNAL_SERVER_ERROR.getStatus());
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class ErrorResponse{
        private int status;
        private String message;
    }
}