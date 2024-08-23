package com.sparks.interview.app.controllers;


import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.sparks.interview.app.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApplicationControllerAdvice {


    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(Exception exception) {
        log.error(exception.getMessage(), exception);
        ModelAndView mav = new ModelAndView("productNotFound");
        mav.addObject("message", exception.getLocalizedMessage());
        return mav;
    }


    @ExceptionHandler(JDBCConnectionException.class)
    public String handleConnectionError(Exception ex) {
        log.error(ex.getMessage(), ex);
        return "connect_error";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>("An Unexpected Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
