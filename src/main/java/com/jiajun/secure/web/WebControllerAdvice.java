package com.jiajun.secure.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

import com.jiajun.secure.core.exception.UnauthenticatedException;

@ControllerAdvice
public class WebControllerAdvice {
	
    @ExceptionHandler(UnauthenticatedException.class)  
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  
    @ResponseBody
    public String processUnauthenticatedException(NativeWebRequest request, UnauthenticatedException e) {  
        return e.getMessage();
    }  
	
}
