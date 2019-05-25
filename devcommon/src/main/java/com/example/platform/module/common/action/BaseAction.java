package com.example.platform.module.common.action;

import javax.servlet.http.HttpServletRequest;

import com.example.platform.module.common.exception.BadRequestException;
import com.example.platform.module.common.exception.ForbiddenException;
import com.example.platform.module.common.exception.ResourceNotFoundException;
import com.example.platform.module.common.response.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 */
public class BaseAction {
	
    private static final Logger logger = LoggerFactory.getLogger(BaseAction.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseError handleError(HttpServletRequest req, Exception ex) {
        logger.error("", ex);
        return new ResponseError( ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    ResponseError handleForbidden(HttpServletRequest req, Exception ex) {
    	return new ResponseError( ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    ResponseError handleNotFound(HttpServletRequest req, Exception ex) {
    	return new ResponseError( ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    ResponseError handleBadRequest(HttpServletRequest req, Exception ex) {
        logger.error("", ex);
        return new ResponseError( ex.getLocalizedMessage());
    }
    
}
