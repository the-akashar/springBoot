package com.eazybytes.eazyschool.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(Exception.class)
    public ModelAndView exeptionHandler(Exception exception){
        ModelAndView errorMessage = new ModelAndView();
        errorMessage.setViewName("error");
        errorMessage.addObject("errormsg" , exception.getMessage());
        return errorMessage;
    }
}
