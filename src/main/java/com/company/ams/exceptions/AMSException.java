package com.company.ams.exceptions;

public class AMSException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String detailMessage;

    public AMSException(ErrorCode errorCode) {
        super(errorCode.getError());
        this.errorCode = errorCode;
        this.detailMessage = null;
    }

    public AMSException(ErrorCode errorCode, String detailMessage) {
        super(detailMessage != null ? detailMessage : errorCode.getError());
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}