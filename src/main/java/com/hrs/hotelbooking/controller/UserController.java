package com.hrs.hotelbooking.controller;

import com.hrs.hotelbooking.dto.request.BookingRequest;
import com.hrs.hotelbooking.dto.response.BookingResponse;
import com.hrs.hotelbooking.exception.BookingException;
import com.hrs.hotelbooking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

/**
 * @author Bhawna-pc
 * */
@RestController
@Slf4j
@RequestMapping("/hrs-hotels/")
public class UserController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/book/{roomId}/")
    public ResponseEntity<BookingResponse> createBooking(@PathVariable("roomId") Integer roomId,
                                                         @RequestBody BookingRequest bookingRequest){
        return new ResponseEntity<>(bookingService.createBooking(roomId, bookingRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get-all-bookings/")
    public ResponseEntity<List<BookingResponse>> getAllBookings(){
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/get-by-booking-id/{bookingId}/")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable("bookingId") Long bookingId){
        return new ResponseEntity<>(bookingService.getByBookingId(bookingId), HttpStatus.OK);
    }

    @PutMapping("/cancel/{bookingId}/")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable("bookingId") Long bookingId) throws BookingException {
        log.info("Booking id " +bookingId);
        return new ResponseEntity<>(bookingService.cancelBooking(bookingId), HttpStatus.ACCEPTED);
    }

}
