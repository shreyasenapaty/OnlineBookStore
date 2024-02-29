package com.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserNotAdminException extends BaseException {
    public static final String INTERNAL_SERVER_ERROR = "Internal Error";

    public UserNotAdminException(String message) {
        super(message);
    }
}
