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

import com.skybayoneoneone.domain.Payment;
import com.skybayoneoneone.dto.PaymentDTO;
import com.skybayoneoneone.dto.PaymentSearchDTO;
import com.skybayoneoneone.dto.PaymentPageDTO;
import com.skybayoneoneone.service.PaymentService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/payment")
@RestController
public class PaymentController {

	private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	PaymentService paymentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Payment> getAll() {

		List<Payment> payments = paymentService.findAll();
		
		return payments;	
	}

	@GetMapping(value = "/{paymentId}")
	@ResponseBody
	public PaymentDTO getPayment(@PathVariable Integer paymentId) {
		
		return (paymentService.getPaymentDTOById(paymentId));
	}

 	@RequestMapping(value = "/addPayment", method = RequestMethod.POST)
	public ResponseEntity<?> addPayment(@RequestBody PaymentDTO paymentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = paymentService.addPayment(paymentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/payments")
	public ResponseEntity<PaymentPageDTO> getPayments(PaymentSearchDTO paymentSearchDTO) {
 
		return paymentService.getPayments(paymentSearchDTO);
	}	

	@RequestMapping(value = "/updatePayment", method = RequestMethod.POST)
	public ResponseEntity<?> updatePayment(@RequestBody PaymentDTO paymentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = paymentService.updatePayment(paymentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
