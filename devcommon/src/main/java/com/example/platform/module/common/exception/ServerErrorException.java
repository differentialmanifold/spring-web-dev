package com.example.platform.module.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * 
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = -6798154278095441848L;

    public ServerErrorException(String s) {
        super(s);
    }

    /**
     * 
     */
    public ServerErrorException() {
        super();
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ServerErrorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public ServerErrorException(Throwable arg0) {
        super(arg0);
    }

}
