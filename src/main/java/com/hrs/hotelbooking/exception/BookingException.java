package com.hrs.hotelbooking.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.awt.print.Book;

/**
 * @author Bhawna-pc
 */
@Data
@AllArgsConstructor

public class BookingException extends RuntimeException{

    private final HttpStatus statusCode;

    private final String message;

}
