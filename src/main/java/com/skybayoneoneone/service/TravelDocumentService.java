package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.TravelDocument;
import com.skybayoneoneone.dto.TravelDocumentDTO;
import com.skybayoneoneone.dto.TravelDocumentSearchDTO;
import com.skybayoneoneone.dto.TravelDocumentPageDTO;
import com.skybayoneoneone.dto.TravelDocumentConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TravelDocumentService extends GenericService<TravelDocument, Integer> {

	List<TravelDocument> findAll();

	ResultDTO addTravelDocument(TravelDocumentDTO travelDocumentDTO, RequestDTO requestDTO);

	ResultDTO updateTravelDocument(TravelDocumentDTO travelDocumentDTO, RequestDTO requestDTO);

    Page<TravelDocument> getAllTravelDocuments(Pageable pageable);

    Page<TravelDocument> getAllTravelDocuments(Specification<TravelDocument> spec, Pageable pageable);

	ResponseEntity<TravelDocumentPageDTO> getTravelDocuments(TravelDocumentSearchDTO travelDocumentSearchDTO);
	
	List<TravelDocumentDTO> convertTravelDocumentsToTravelDocumentDTOs(List<TravelDocument> travelDocuments, TravelDocumentConvertCriteriaDTO convertCriteria);

	TravelDocumentDTO getTravelDocumentDTOById(Integer travelDocumentId);







}





