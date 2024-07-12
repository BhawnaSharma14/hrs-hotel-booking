package com.hrs.hotelbooking.service.impl;

import com.hrs.hotelbooking.dto.request.BookingRequest;
import com.hrs.hotelbooking.dto.response.BookingResponse;
import com.hrs.hotelbooking.enums.BookingStatus;
import com.hrs.hotelbooking.exception.BookingException;
import com.hrs.hotelbooking.model.Booking;
import com.hrs.hotelbooking.model.User;
import com.hrs.hotelbooking.repository.BookingDao;
import com.hrs.hotelbooking.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Bhawna-pc
 */
@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingDao bookingDao;

    /**
     * Method to create the Booking
     *
     * @param roomId Integer
     * @param bookingRequest BookingRequest
     * @return String
     * @throws BookingException
     */
    public BookingResponse createBooking(Integer roomId, BookingRequest bookingRequest){
        log.info("Creating Booking");
        Booking booking = createBooking(bookingRequest, roomId);
        User user = bookingRequest.getUser();
        List<Booking> list = new ArrayList<Booking>();
//        list.add(user.getBookings().getFirst());
        list.add(booking);
        user.setBookings(list);
        //user.getBookings().add(booking);

        booking.setUser(user);
        bookingDao.save(booking);

        log.info("Booking Created Successfully");
        return createBookingResponse(booking);
    }

    /**
     * Method to get all the Bookings
     *
     * @return List<BookingResponse>
     * @throws BookingException
     */
    @Override
    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings = bookingDao.findAll();
        if(bookings.isEmpty()){
            throw new BookingException(HttpStatus.NOT_FOUND, "No Bookings Found!!!");
        }
        return bookings.stream().map(this::createBookingResponse).collect(Collectors.toList());
    }

    /**
     * Method to get the booking by Booking ID
     *
     * @param bookingId Long
     * @return BookingResponse
     * @throws BookingException
     */
    @Override
    public BookingResponse getByBookingId(Long bookingId) {
        Optional<Booking> bookingOpt = bookingDao.findById(bookingId);
        if(bookingOpt.isEmpty()){
            throw new BookingException(HttpStatus.NOT_FOUND, "Booking not found with this Booking Id :: "+bookingId);
        }
        return createBookingResponse(bookingOpt.get());
    }

    /**
     * Method to cancel the Booking
     *
     * @param bookingId Long
     * @return String
     * @throws BookingException
     */
    public BookingResponse cancelBooking(Long bookingId) {
        Optional<Booking> bookingOpt = bookingDao.findById(bookingId);
        if(bookingOpt.isEmpty()){
            throw new BookingException(HttpStatus.NOT_FOUND, "Booking not found with this Booking Id :: "+bookingId);
        }else{
            Booking booking = bookingOpt.get();
            User user = booking.getUser();
            booking.setStatus(BookingStatus.CLOSED);
            //Remove booking from user
            List<Booking> userBookings = user.getBookings();
            userBookings.remove(booking);
            user.setBookings(userBookings);
            bookingDao.deleteById(booking.getBookingId());
            log.info("Deleted");
            return createBookingResponse(booking);
        }
    }

    /**
     * Method to create a Booking
     *
     * @param bookingRequest BookingRequest
     * @param roomId Integer
     * @return Booking
     */
    private Booking createBooking(BookingRequest bookingRequest, Integer roomId){
        return Booking.builder()
                .checkinDate(bookingRequest.getCheckInDate())
                .checkoutDate(bookingRequest.getCheckOutDate())
                .noOfPerson(bookingRequest.getNoOfPerson())
                .status(BookingStatus.BOOKED)
                .roomNo(roomId)
                .build();
    }

    /**
     * Method to generate a Booking Response
     *
     * @param booking Booking
     * @return BookingResponse
     */
    private BookingResponse createBookingResponse(Booking booking){
        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .bookingStatus(booking.getStatus())
                .checkInDate(booking.getCheckinDate())
                .checkOutDate(booking.getCheckoutDate())
                .userName(booking.getUser().getName())
                .noOfPerson(booking.getNoOfPerson())
                .roomNo(booking.getRoomNo())
                .build();
    }
}
