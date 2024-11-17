package com.skybayoneoneone.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skybayoneoneone.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skybayoneoneone.domain.FlightCrew;
import com.skybayoneoneone.dto.FlightCrewDTO;
import com.skybayoneoneone.dto.FlightCrewSearchDTO;
import com.skybayoneoneone.dto.FlightCrewPageDTO;
import com.skybayoneoneone.service.FlightCrewService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/flightCrew")
@RestController
public class FlightCrewController {

	private final static Logger logger = LoggerFactory.getLogger(FlightCrewController.class);

	@Autowired
	FlightCrewService flightCrewService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<FlightCrew> getAll() {

		List<FlightCrew> flightCrews = flightCrewService.findAll();
		
		return flightCrews;	
	}

	@GetMapping(value = "/{flightCrewId}")
	@ResponseBody
	public FlightCrewDTO getFlightCrew(@PathVariable Integer flightCrewId) {
		
		return (flightCrewService.getFlightCrewDTOById(flightCrewId));
	}

 	@RequestMapping(value = "/addFlightCrew", method = RequestMethod.POST)
	public ResponseEntity<?> addFlightCrew(@RequestBody FlightCrewDTO flightCrewDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flightCrewService.addFlightCrew(flightCrewDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/flightCrews")
	public ResponseEntity<FlightCrewPageDTO> getFlightCrews(FlightCrewSearchDTO flightCrewSearchDTO) {
 
		return flightCrewService.getFlightCrews(flightCrewSearchDTO);
	}	

	@RequestMapping(value = "/updateFlightCrew", method = RequestMethod.POST)
	public ResponseEntity<?> updateFlightCrew(@RequestBody FlightCrewDTO flightCrewDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flightCrewService.updateFlightCrew(flightCrewDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
