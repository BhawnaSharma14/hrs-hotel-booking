package com.hrs.hotelbooking.service;

import com.hrs.hotelbooking.dto.request.BookingRequest;
import com.hrs.hotelbooking.dto.response.BookingResponse;

import java.awt.print.Book;
import java.util.List;

/**
 * @author Bhawna-pc
 */

public interface BookingService {


    public BookingResponse createBooking(Integer roomId, BookingRequest bookingRequest);
    public List<BookingResponse> getAllBookings();
    public BookingResponse getByBookingId(Long bookingId);
    public BookingResponse cancelBooking(Long bookingId);
}
