/**
 ************************** FACIAL RECOGNITION - CAPSTONE ************************
 * Exception Handler
 * @author  STEALTH
 *
 */
package com.stealth.yash.FaceRecognition.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Method that throws exception when anything is not found
     */
    public NotFoundException() {
        super();
    }

    /**
     *Method that throws exception when anything is not found
     * Message is passed through the method
     *  @param message an object of type String
     *
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     *Method that throws exception when anything is not found
     * Message and cause of the exception is passed through the method
     *  @param message an object of type String
     * @param cause an object of Throwable type
     */

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}