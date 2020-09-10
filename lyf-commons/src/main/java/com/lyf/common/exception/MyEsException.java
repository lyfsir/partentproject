package com.lyf.common.exception;

/**
 * @author lyf
 * @date 2020/8/20-12:47
 */
public class MyEsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public MyEsException(){
        super();
    }
    public MyEsException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyEsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyEsException(String message) {
        super(message);
    }

    public MyEsException(Throwable cause) {
        super(cause);
    }

}
