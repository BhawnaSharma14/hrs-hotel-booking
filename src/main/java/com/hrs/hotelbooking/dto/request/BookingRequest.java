package com.hrs.hotelbooking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hrs.hotelbooking.model.User;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Bhawna-pc
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @FutureOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkInDate;

    @FutureOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkOutDate;

    @Min(1)
    private Integer noOfPerson;

    private User user;
}
