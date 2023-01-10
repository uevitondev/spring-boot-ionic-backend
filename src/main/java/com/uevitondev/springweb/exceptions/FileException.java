package com.uevitondev.springweb.exceptions;

import java.io.Serial;

public class FileException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2733283887248077735L;

    public FileException(String msg) {
        super(msg);
    }

    public FileException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
