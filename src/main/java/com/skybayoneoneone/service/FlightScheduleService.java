package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.FlightSchedule;
import com.skybayoneoneone.dto.FlightScheduleDTO;
import com.skybayoneoneone.dto.FlightScheduleSearchDTO;
import com.skybayoneoneone.dto.FlightSchedulePageDTO;
import com.skybayoneoneone.dto.FlightScheduleConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FlightScheduleService extends GenericService<FlightSchedule, Integer> {

	List<FlightSchedule> findAll();

	ResultDTO addFlightSchedule(FlightScheduleDTO flightScheduleDTO, RequestDTO requestDTO);

	ResultDTO updateFlightSchedule(FlightScheduleDTO flightScheduleDTO, RequestDTO requestDTO);

    Page<FlightSchedule> getAllFlightSchedules(Pageable pageable);

    Page<FlightSchedule> getAllFlightSchedules(Specification<FlightSchedule> spec, Pageable pageable);

	ResponseEntity<FlightSchedulePageDTO> getFlightSchedules(FlightScheduleSearchDTO flightScheduleSearchDTO);
	
	List<FlightScheduleDTO> convertFlightSchedulesToFlightScheduleDTOs(List<FlightSchedule> flightSchedules, FlightScheduleConvertCriteriaDTO convertCriteria);

	FlightScheduleDTO getFlightScheduleDTOById(Integer flightScheduleId);







}





