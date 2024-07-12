package com.hrs.hotelbooking.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Bhawna-pc
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationError {

    private LocalDateTime timestamp;
    private String message;
    private HttpStatus errorStatusCode;


}
