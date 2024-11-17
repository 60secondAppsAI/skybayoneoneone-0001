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

import com.skybayoneoneone.domain.FlightSchedule;
import com.skybayoneoneone.dto.FlightScheduleDTO;
import com.skybayoneoneone.dto.FlightScheduleSearchDTO;
import com.skybayoneoneone.dto.FlightSchedulePageDTO;
import com.skybayoneoneone.service.FlightScheduleService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/flightSchedule")
@RestController
public class FlightScheduleController {

	private final static Logger logger = LoggerFactory.getLogger(FlightScheduleController.class);

	@Autowired
	FlightScheduleService flightScheduleService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<FlightSchedule> getAll() {

		List<FlightSchedule> flightSchedules = flightScheduleService.findAll();
		
		return flightSchedules;	
	}

	@GetMapping(value = "/{flightScheduleId}")
	@ResponseBody
	public FlightScheduleDTO getFlightSchedule(@PathVariable Integer flightScheduleId) {
		
		return (flightScheduleService.getFlightScheduleDTOById(flightScheduleId));
	}

 	@RequestMapping(value = "/addFlightSchedule", method = RequestMethod.POST)
	public ResponseEntity<?> addFlightSchedule(@RequestBody FlightScheduleDTO flightScheduleDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flightScheduleService.addFlightSchedule(flightScheduleDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/flightSchedules")
	public ResponseEntity<FlightSchedulePageDTO> getFlightSchedules(FlightScheduleSearchDTO flightScheduleSearchDTO) {
 
		return flightScheduleService.getFlightSchedules(flightScheduleSearchDTO);
	}	

	@RequestMapping(value = "/updateFlightSchedule", method = RequestMethod.POST)
	public ResponseEntity<?> updateFlightSchedule(@RequestBody FlightScheduleDTO flightScheduleDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flightScheduleService.updateFlightSchedule(flightScheduleDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
