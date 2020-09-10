package com.lyf.common.exception;

/**
 * @author lyf
 * @date 2020/8/23-5:22
 */
public class JwtOutException extends Exception {
    private static final long serialVersionUID = 1L;
    public JwtOutException(){
        super();
    }
    public JwtOutException(String message, Throwable cause, boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public JwtOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtOutException(String message) {
        super(message);
    }

    public JwtOutException(Throwable cause) {
        super(cause);
    }
}
