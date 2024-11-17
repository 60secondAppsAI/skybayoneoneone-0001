package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.BoardingPass;
import com.skybayoneoneone.dto.BoardingPassDTO;
import com.skybayoneoneone.dto.BoardingPassSearchDTO;
import com.skybayoneoneone.dto.BoardingPassPageDTO;
import com.skybayoneoneone.dto.BoardingPassConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BoardingPassService extends GenericService<BoardingPass, Integer> {

	List<BoardingPass> findAll();

	ResultDTO addBoardingPass(BoardingPassDTO boardingPassDTO, RequestDTO requestDTO);

	ResultDTO updateBoardingPass(BoardingPassDTO boardingPassDTO, RequestDTO requestDTO);

    Page<BoardingPass> getAllBoardingPasss(Pageable pageable);

    Page<BoardingPass> getAllBoardingPasss(Specification<BoardingPass> spec, Pageable pageable);

	ResponseEntity<BoardingPassPageDTO> getBoardingPasss(BoardingPassSearchDTO boardingPassSearchDTO);
	
	List<BoardingPassDTO> convertBoardingPasssToBoardingPassDTOs(List<BoardingPass> boardingPasss, BoardingPassConvertCriteriaDTO convertCriteria);

	BoardingPassDTO getBoardingPassDTOById(Integer boardingPassId);







}





