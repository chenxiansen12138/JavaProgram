package com.chen.xiansen.webflux.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public String error(ArithmeticException e) {
        System.out.println("发生了运算异常" + e);
        return e.getLocalizedMessage();
    }
}
