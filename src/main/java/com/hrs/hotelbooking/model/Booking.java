package com.hrs.hotelbooking.model;

import com.hrs.hotelbooking.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

/** Represents Booking Details.
 * @author Bhawna-pc
 */
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;

    private LocalDate checkinDate;

    private LocalDate checkoutDate;

    private Integer noOfPerson;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private User user;

    private Integer roomNo;
}
