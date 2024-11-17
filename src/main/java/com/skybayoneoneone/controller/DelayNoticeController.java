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

import com.skybayoneoneone.domain.DelayNotice;
import com.skybayoneoneone.dto.DelayNoticeDTO;
import com.skybayoneoneone.dto.DelayNoticeSearchDTO;
import com.skybayoneoneone.dto.DelayNoticePageDTO;
import com.skybayoneoneone.service.DelayNoticeService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/delayNotice")
@RestController
public class DelayNoticeController {

	private final static Logger logger = LoggerFactory.getLogger(DelayNoticeController.class);

	@Autowired
	DelayNoticeService delayNoticeService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<DelayNotice> getAll() {

		List<DelayNotice> delayNotices = delayNoticeService.findAll();
		
		return delayNotices;	
	}

	@GetMapping(value = "/{delayNoticeId}")
	@ResponseBody
	public DelayNoticeDTO getDelayNotice(@PathVariable Integer delayNoticeId) {
		
		return (delayNoticeService.getDelayNoticeDTOById(delayNoticeId));
	}

 	@RequestMapping(value = "/addDelayNotice", method = RequestMethod.POST)
	public ResponseEntity<?> addDelayNotice(@RequestBody DelayNoticeDTO delayNoticeDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = delayNoticeService.addDelayNotice(delayNoticeDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/delayNotices")
	public ResponseEntity<DelayNoticePageDTO> getDelayNotices(DelayNoticeSearchDTO delayNoticeSearchDTO) {
 
		return delayNoticeService.getDelayNotices(delayNoticeSearchDTO);
	}	

	@RequestMapping(value = "/updateDelayNotice", method = RequestMethod.POST)
	public ResponseEntity<?> updateDelayNotice(@RequestBody DelayNoticeDTO delayNoticeDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = delayNoticeService.updateDelayNotice(delayNoticeDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
