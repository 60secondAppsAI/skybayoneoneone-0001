package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.FlightCrew;
import com.skybayoneoneone.dto.FlightCrewDTO;
import com.skybayoneoneone.dto.FlightCrewSearchDTO;
import com.skybayoneoneone.dto.FlightCrewPageDTO;
import com.skybayoneoneone.dto.FlightCrewConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FlightCrewService extends GenericService<FlightCrew, Integer> {

	List<FlightCrew> findAll();

	ResultDTO addFlightCrew(FlightCrewDTO flightCrewDTO, RequestDTO requestDTO);

	ResultDTO updateFlightCrew(FlightCrewDTO flightCrewDTO, RequestDTO requestDTO);

    Page<FlightCrew> getAllFlightCrews(Pageable pageable);

    Page<FlightCrew> getAllFlightCrews(Specification<FlightCrew> spec, Pageable pageable);

	ResponseEntity<FlightCrewPageDTO> getFlightCrews(FlightCrewSearchDTO flightCrewSearchDTO);
	
	List<FlightCrewDTO> convertFlightCrewsToFlightCrewDTOs(List<FlightCrew> flightCrews, FlightCrewConvertCriteriaDTO convertCriteria);

	FlightCrewDTO getFlightCrewDTOById(Integer flightCrewId);







}





