package com.company.ams.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "ResourceNotFound"),
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "UNPROCESSABLE_ENTITY"),
    UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED, "UnauthorizedRequest"),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "Unauthorized User"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "InvalidRequest"),
    AUTHORIZATION_ERROR(HttpStatus.FORBIDDEN, "AuthorizationError"),
    INVALID_HEADER(HttpStatus.BAD_REQUEST, "MissingRequiredHeader"),
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, "NotImplementedError"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "ValidationError"),
    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "InvalidAccessTokenError"),
    INVALID_INTERNAL_TOKEN(HttpStatus.FORBIDDEN, "InvalidInternalToken"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "InternalServerError"),
    EMAIL_NOT_FOUND(HttpStatus.BAD_REQUEST, "EmailNotFound"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TokenExpired"),
    INVALID_AUTHORIZATION_TOKEN(HttpStatus.BAD_REQUEST, "InvalidAuthorizationToken"),
    LIMIT_EXCEEDED_ERROR(HttpStatus.TOO_MANY_REQUESTS, "LimitExceedError");

    HttpStatus status;
    String error;

    ErrorCode(HttpStatus status, String error) {
        this.status = status;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
