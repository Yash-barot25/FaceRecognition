package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){

        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("exception/exceptionpanel");
        modelAndView.addObject("statusCode", "400");
        modelAndView.addObject("type", "Bad Request");
        modelAndView.addObject("exception", exception.getMessage());

        return modelAndView;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling Not Found Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("exception/exceptionpanel");
        modelAndView.addObject("statusCode", "404");
        modelAndView.addObject("type", "Resource Not Found");
        modelAndView.addObject("exception", exception.getMessage());

        return modelAndView;
    }
}