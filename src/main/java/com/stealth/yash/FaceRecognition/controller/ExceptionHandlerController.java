/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * This Controller is responsible for handling exceptions
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.controller;

import com.stealth.yash.FaceRecognition.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


// Causes lombok to generate a logger field
@Slf4j
// ensures this controller applied to a other controllers
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    /**
     * Throws exception when there is error in Number format
     * @param exception this is an object of type Exception
     * @return model and view in a single value
     */
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

    /**
     * Throws exception when anything is not found
     * @param exception this is an object of type Exception
     * @return model and view in a single value
     */
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