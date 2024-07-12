package com.hrs.hotelbooking.repository;

import com.hrs.hotelbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bhawna-pc
 */
@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {
}
