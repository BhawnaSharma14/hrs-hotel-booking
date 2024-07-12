package com.hrs.hotelbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author Bhawna-pc
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(BookingException.class)
        public ApplicationError handleBookingException(BookingException bookingException){
            return new ApplicationError(LocalDateTime.now(), bookingException.getMessage(), bookingException.getStatusCode());
        }

        @ExceptionHandler(Exception.class)
        public ApplicationError handleGeneralException(Exception exception){
            return new ApplicationError(LocalDateTime.now(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
