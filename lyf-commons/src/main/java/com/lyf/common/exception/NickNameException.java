package com.lyf.common.exception;

/**
 * @author lyf
 * @date 2020/6/23-4:08
 */
public class NickNameException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NickNameException() {
        super();
    }

    public NickNameException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NickNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NickNameException(String message) {
        super(message);
    }

    public NickNameException(Throwable cause) {
        super(cause);
    }

}
