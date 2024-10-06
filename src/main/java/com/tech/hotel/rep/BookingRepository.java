package com.tech.hotel.rep;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.hotel.entity.Booking;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	 Optional<Booking> findByBookingConfirmationCode(String confirmationCode);

}
