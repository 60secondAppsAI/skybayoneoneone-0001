package com.skybayoneoneone.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skybayoneoneone.dao.GenericDAO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.service.impl.GenericServiceImpl;
import com.skybayoneoneone.dao.TravelDocumentDAO;
import com.skybayoneoneone.domain.TravelDocument;
import com.skybayoneoneone.dto.TravelDocumentDTO;
import com.skybayoneoneone.dto.TravelDocumentSearchDTO;
import com.skybayoneoneone.dto.TravelDocumentPageDTO;
import com.skybayoneoneone.dto.TravelDocumentConvertCriteriaDTO;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import com.skybayoneoneone.service.TravelDocumentService;
import com.skybayoneoneone.util.ControllerUtils;





@Service
public class TravelDocumentServiceImpl extends GenericServiceImpl<TravelDocument, Integer> implements TravelDocumentService {

    private final static Logger logger = LoggerFactory.getLogger(TravelDocumentServiceImpl.class);

	@Autowired
	TravelDocumentDAO travelDocumentDao;

	


	@Override
	public GenericDAO<TravelDocument, Integer> getDAO() {
		return (GenericDAO<TravelDocument, Integer>) travelDocumentDao;
	}
	
	public List<TravelDocument> findAll () {
		List<TravelDocument> travelDocuments = travelDocumentDao.findAll();
		
		return travelDocuments;	
		
	}

	public ResultDTO addTravelDocument(TravelDocumentDTO travelDocumentDTO, RequestDTO requestDTO) {

		TravelDocument travelDocument = new TravelDocument();

		travelDocument.setTravelDocumentId(travelDocumentDTO.getTravelDocumentId());


		travelDocument.setDocumentType(travelDocumentDTO.getDocumentType());


		travelDocument.setIssueDate(travelDocumentDTO.getIssueDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		travelDocument = travelDocumentDao.save(travelDocument);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<TravelDocument> getAllTravelDocuments(Pageable pageable) {
		return travelDocumentDao.findAll(pageable);
	}

	public Page<TravelDocument> getAllTravelDocuments(Specification<TravelDocument> spec, Pageable pageable) {
		return travelDocumentDao.findAll(spec, pageable);
	}

	public ResponseEntity<TravelDocumentPageDTO> getTravelDocuments(TravelDocumentSearchDTO travelDocumentSearchDTO) {
	
			Integer travelDocumentId = travelDocumentSearchDTO.getTravelDocumentId(); 
 			String documentType = travelDocumentSearchDTO.getDocumentType(); 
   			String sortBy = travelDocumentSearchDTO.getSortBy();
			String sortOrder = travelDocumentSearchDTO.getSortOrder();
			String searchQuery = travelDocumentSearchDTO.getSearchQuery();
			Integer page = travelDocumentSearchDTO.getPage();
			Integer size = travelDocumentSearchDTO.getSize();

	        Specification<TravelDocument> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, travelDocumentId, "travelDocumentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, documentType, "documentType"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("documentType")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<TravelDocument> travelDocuments = this.getAllTravelDocuments(spec, pageable);
		
		//System.out.println(String.valueOf(travelDocuments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(travelDocuments.getTotalPages()));
		
		List<TravelDocument> travelDocumentsList = travelDocuments.getContent();
		
		TravelDocumentConvertCriteriaDTO convertCriteria = new TravelDocumentConvertCriteriaDTO();
		List<TravelDocumentDTO> travelDocumentDTOs = this.convertTravelDocumentsToTravelDocumentDTOs(travelDocumentsList,convertCriteria);
		
		TravelDocumentPageDTO travelDocumentPageDTO = new TravelDocumentPageDTO();
		travelDocumentPageDTO.setTravelDocuments(travelDocumentDTOs);
		travelDocumentPageDTO.setTotalElements(travelDocuments.getTotalElements());
		return ResponseEntity.ok(travelDocumentPageDTO);
	}

	public List<TravelDocumentDTO> convertTravelDocumentsToTravelDocumentDTOs(List<TravelDocument> travelDocuments, TravelDocumentConvertCriteriaDTO convertCriteria) {
		
		List<TravelDocumentDTO> travelDocumentDTOs = new ArrayList<TravelDocumentDTO>();
		
		for (TravelDocument travelDocument : travelDocuments) {
			travelDocumentDTOs.add(convertTravelDocumentToTravelDocumentDTO(travelDocument,convertCriteria));
		}
		
		return travelDocumentDTOs;

	}
	
	public TravelDocumentDTO convertTravelDocumentToTravelDocumentDTO(TravelDocument travelDocument, TravelDocumentConvertCriteriaDTO convertCriteria) {
		
		TravelDocumentDTO travelDocumentDTO = new TravelDocumentDTO();
		
		travelDocumentDTO.setTravelDocumentId(travelDocument.getTravelDocumentId());

	
		travelDocumentDTO.setDocumentType(travelDocument.getDocumentType());

	
		travelDocumentDTO.setIssueDate(travelDocument.getIssueDate());

	

		
		return travelDocumentDTO;
	}

	public ResultDTO updateTravelDocument(TravelDocumentDTO travelDocumentDTO, RequestDTO requestDTO) {
		
		TravelDocument travelDocument = travelDocumentDao.getById(travelDocumentDTO.getTravelDocumentId());

		travelDocument.setTravelDocumentId(ControllerUtils.setValue(travelDocument.getTravelDocumentId(), travelDocumentDTO.getTravelDocumentId()));

		travelDocument.setDocumentType(ControllerUtils.setValue(travelDocument.getDocumentType(), travelDocumentDTO.getDocumentType()));

		travelDocument.setIssueDate(ControllerUtils.setValue(travelDocument.getIssueDate(), travelDocumentDTO.getIssueDate()));



        travelDocument = travelDocumentDao.save(travelDocument);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TravelDocumentDTO getTravelDocumentDTOById(Integer travelDocumentId) {
	
		TravelDocument travelDocument = travelDocumentDao.getById(travelDocumentId);
			
		
		TravelDocumentConvertCriteriaDTO convertCriteria = new TravelDocumentConvertCriteriaDTO();
		return(this.convertTravelDocumentToTravelDocumentDTO(travelDocument,convertCriteria));
	}







}
