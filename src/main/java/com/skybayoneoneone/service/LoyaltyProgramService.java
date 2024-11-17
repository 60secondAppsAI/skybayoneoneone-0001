package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.LoyaltyProgram;
import com.skybayoneoneone.dto.LoyaltyProgramDTO;
import com.skybayoneoneone.dto.LoyaltyProgramSearchDTO;
import com.skybayoneoneone.dto.LoyaltyProgramPageDTO;
import com.skybayoneoneone.dto.LoyaltyProgramConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LoyaltyProgramService extends GenericService<LoyaltyProgram, Integer> {

	List<LoyaltyProgram> findAll();

	ResultDTO addLoyaltyProgram(LoyaltyProgramDTO loyaltyProgramDTO, RequestDTO requestDTO);

	ResultDTO updateLoyaltyProgram(LoyaltyProgramDTO loyaltyProgramDTO, RequestDTO requestDTO);

    Page<LoyaltyProgram> getAllLoyaltyPrograms(Pageable pageable);

    Page<LoyaltyProgram> getAllLoyaltyPrograms(Specification<LoyaltyProgram> spec, Pageable pageable);

	ResponseEntity<LoyaltyProgramPageDTO> getLoyaltyPrograms(LoyaltyProgramSearchDTO loyaltyProgramSearchDTO);
	
	List<LoyaltyProgramDTO> convertLoyaltyProgramsToLoyaltyProgramDTOs(List<LoyaltyProgram> loyaltyPrograms, LoyaltyProgramConvertCriteriaDTO convertCriteria);

	LoyaltyProgramDTO getLoyaltyProgramDTOById(Integer loyaltyProgramId);







}





