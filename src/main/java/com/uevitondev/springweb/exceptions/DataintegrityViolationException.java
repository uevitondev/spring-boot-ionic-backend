package com.uevitondev.springweb.exceptions;

import java.io.Serial;

public class DataintegrityViolationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2733283887248077735L;

    public DataintegrityViolationException(String msg) {
        super(msg);
    }

    public DataintegrityViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
