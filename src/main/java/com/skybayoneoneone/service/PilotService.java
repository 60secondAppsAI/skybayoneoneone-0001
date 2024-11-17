package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.Pilot;
import com.skybayoneoneone.dto.PilotDTO;
import com.skybayoneoneone.dto.PilotSearchDTO;
import com.skybayoneoneone.dto.PilotPageDTO;
import com.skybayoneoneone.dto.PilotConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PilotService extends GenericService<Pilot, Integer> {

	List<Pilot> findAll();

	ResultDTO addPilot(PilotDTO pilotDTO, RequestDTO requestDTO);

	ResultDTO updatePilot(PilotDTO pilotDTO, RequestDTO requestDTO);

    Page<Pilot> getAllPilots(Pageable pageable);

    Page<Pilot> getAllPilots(Specification<Pilot> spec, Pageable pageable);

	ResponseEntity<PilotPageDTO> getPilots(PilotSearchDTO pilotSearchDTO);
	
	List<PilotDTO> convertPilotsToPilotDTOs(List<Pilot> pilots, PilotConvertCriteriaDTO convertCriteria);

	PilotDTO getPilotDTOById(Integer pilotId);







}





