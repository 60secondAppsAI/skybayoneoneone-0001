package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.Booking;
import com.skybayoneoneone.dto.BookingDTO;
import com.skybayoneoneone.dto.BookingSearchDTO;
import com.skybayoneoneone.dto.BookingPageDTO;
import com.skybayoneoneone.dto.BookingConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BookingService extends GenericService<Booking, Integer> {

	List<Booking> findAll();

	ResultDTO addBooking(BookingDTO bookingDTO, RequestDTO requestDTO);

	ResultDTO updateBooking(BookingDTO bookingDTO, RequestDTO requestDTO);

    Page<Booking> getAllBookings(Pageable pageable);

    Page<Booking> getAllBookings(Specification<Booking> spec, Pageable pageable);

	ResponseEntity<BookingPageDTO> getBookings(BookingSearchDTO bookingSearchDTO);
	
	List<BookingDTO> convertBookingsToBookingDTOs(List<Booking> bookings, BookingConvertCriteriaDTO convertCriteria);

	BookingDTO getBookingDTOById(Integer bookingId);







}





