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

import com.skybayoneoneone.domain.TravelDocument;
import com.skybayoneoneone.dto.TravelDocumentDTO;
import com.skybayoneoneone.dto.TravelDocumentSearchDTO;
import com.skybayoneoneone.dto.TravelDocumentPageDTO;
import com.skybayoneoneone.service.TravelDocumentService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/travelDocument")
@RestController
public class TravelDocumentController {

	private final static Logger logger = LoggerFactory.getLogger(TravelDocumentController.class);

	@Autowired
	TravelDocumentService travelDocumentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<TravelDocument> getAll() {

		List<TravelDocument> travelDocuments = travelDocumentService.findAll();
		
		return travelDocuments;	
	}

	@GetMapping(value = "/{travelDocumentId}")
	@ResponseBody
	public TravelDocumentDTO getTravelDocument(@PathVariable Integer travelDocumentId) {
		
		return (travelDocumentService.getTravelDocumentDTOById(travelDocumentId));
	}

 	@RequestMapping(value = "/addTravelDocument", method = RequestMethod.POST)
	public ResponseEntity<?> addTravelDocument(@RequestBody TravelDocumentDTO travelDocumentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = travelDocumentService.addTravelDocument(travelDocumentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/travelDocuments")
	public ResponseEntity<TravelDocumentPageDTO> getTravelDocuments(TravelDocumentSearchDTO travelDocumentSearchDTO) {
 
		return travelDocumentService.getTravelDocuments(travelDocumentSearchDTO);
	}	

	@RequestMapping(value = "/updateTravelDocument", method = RequestMethod.POST)
	public ResponseEntity<?> updateTravelDocument(@RequestBody TravelDocumentDTO travelDocumentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = travelDocumentService.updateTravelDocument(travelDocumentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
