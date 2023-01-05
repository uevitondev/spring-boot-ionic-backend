package com.uevitondev.springweb.exceptions;

import java.io.Serial;

public class AuthorizationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2733283887248077735L;

    public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
