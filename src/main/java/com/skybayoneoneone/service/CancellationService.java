package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.Cancellation;
import com.skybayoneoneone.dto.CancellationDTO;
import com.skybayoneoneone.dto.CancellationSearchDTO;
import com.skybayoneoneone.dto.CancellationPageDTO;
import com.skybayoneoneone.dto.CancellationConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CancellationService extends GenericService<Cancellation, Integer> {

	List<Cancellation> findAll();

	ResultDTO addCancellation(CancellationDTO cancellationDTO, RequestDTO requestDTO);

	ResultDTO updateCancellation(CancellationDTO cancellationDTO, RequestDTO requestDTO);

    Page<Cancellation> getAllCancellations(Pageable pageable);

    Page<Cancellation> getAllCancellations(Specification<Cancellation> spec, Pageable pageable);

	ResponseEntity<CancellationPageDTO> getCancellations(CancellationSearchDTO cancellationSearchDTO);
	
	List<CancellationDTO> convertCancellationsToCancellationDTOs(List<Cancellation> cancellations, CancellationConvertCriteriaDTO convertCriteria);

	CancellationDTO getCancellationDTOById(Integer cancellationId);







}





