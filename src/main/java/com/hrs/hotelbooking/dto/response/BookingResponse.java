package com.hrs.hotelbooking.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hrs.hotelbooking.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Bhawna-pc
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

    private Long bookingId;

    private String userName;

    private Integer noOfPerson;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkInDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkOutDate;

    private Integer roomNo;

    private BookingStatus bookingStatus;
}
