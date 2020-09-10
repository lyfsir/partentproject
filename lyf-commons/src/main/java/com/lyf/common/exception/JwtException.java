package com.lyf.common.exception;

/**
 * @author lyf
 * @date 2020/8/23-5:16
 */
public class JwtException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public JwtException(){
        super();
    }
    public JwtException(String message, Throwable cause, boolean enableSuppression,
                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public JwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtException(String message) {
        super(message);
    }

    public JwtException(Throwable cause) {
        super(cause);
    }
}
