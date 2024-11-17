package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.CheckIn;
import com.skybayoneoneone.dto.CheckInDTO;
import com.skybayoneoneone.dto.CheckInSearchDTO;
import com.skybayoneoneone.dto.CheckInPageDTO;
import com.skybayoneoneone.dto.CheckInConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CheckInService extends GenericService<CheckIn, Integer> {

	List<CheckIn> findAll();

	ResultDTO addCheckIn(CheckInDTO checkInDTO, RequestDTO requestDTO);

	ResultDTO updateCheckIn(CheckInDTO checkInDTO, RequestDTO requestDTO);

    Page<CheckIn> getAllCheckIns(Pageable pageable);

    Page<CheckIn> getAllCheckIns(Specification<CheckIn> spec, Pageable pageable);

	ResponseEntity<CheckInPageDTO> getCheckIns(CheckInSearchDTO checkInSearchDTO);
	
	List<CheckInDTO> convertCheckInsToCheckInDTOs(List<CheckIn> checkIns, CheckInConvertCriteriaDTO convertCriteria);

	CheckInDTO getCheckInDTOById(Integer checkInId);







}





