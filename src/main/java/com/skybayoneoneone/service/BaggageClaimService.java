package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.BaggageClaim;
import com.skybayoneoneone.dto.BaggageClaimDTO;
import com.skybayoneoneone.dto.BaggageClaimSearchDTO;
import com.skybayoneoneone.dto.BaggageClaimPageDTO;
import com.skybayoneoneone.dto.BaggageClaimConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BaggageClaimService extends GenericService<BaggageClaim, Integer> {

	List<BaggageClaim> findAll();

	ResultDTO addBaggageClaim(BaggageClaimDTO baggageClaimDTO, RequestDTO requestDTO);

	ResultDTO updateBaggageClaim(BaggageClaimDTO baggageClaimDTO, RequestDTO requestDTO);

    Page<BaggageClaim> getAllBaggageClaims(Pageable pageable);

    Page<BaggageClaim> getAllBaggageClaims(Specification<BaggageClaim> spec, Pageable pageable);

	ResponseEntity<BaggageClaimPageDTO> getBaggageClaims(BaggageClaimSearchDTO baggageClaimSearchDTO);
	
	List<BaggageClaimDTO> convertBaggageClaimsToBaggageClaimDTOs(List<BaggageClaim> baggageClaims, BaggageClaimConvertCriteriaDTO convertCriteria);

	BaggageClaimDTO getBaggageClaimDTOById(Integer baggageClaimId);







}





