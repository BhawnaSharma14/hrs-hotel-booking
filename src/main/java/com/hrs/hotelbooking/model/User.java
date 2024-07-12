package com.hrs.hotelbooking.model;

import com.hrs.hotelbooking.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

/** Represents a user.
 * @author Bhawna-pc
 */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //check
    private Long userId;

    private String name;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Booking> bookings;
}
