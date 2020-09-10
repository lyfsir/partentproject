package com.lyf.common.exception;

/**
 * @author lyf
 * @date 2020/6/23-4:09
 */
public class MobileException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MobileException() {
        super();
    }

    public MobileException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MobileException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileException(String message) {
        super(message);
    }

    public MobileException(Throwable cause) {
        super(cause);
    }
}
