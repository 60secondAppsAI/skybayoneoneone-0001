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
import com.skybayoneoneone.dao.CancellationDAO;
import com.skybayoneoneone.domain.Cancellation;
import com.skybayoneoneone.dto.CancellationDTO;
import com.skybayoneoneone.dto.CancellationSearchDTO;
import com.skybayoneoneone.dto.CancellationPageDTO;
import com.skybayoneoneone.dto.CancellationConvertCriteriaDTO;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import com.skybayoneoneone.service.CancellationService;
import com.skybayoneoneone.util.ControllerUtils;





@Service
public class CancellationServiceImpl extends GenericServiceImpl<Cancellation, Integer> implements CancellationService {

    private final static Logger logger = LoggerFactory.getLogger(CancellationServiceImpl.class);

	@Autowired
	CancellationDAO cancellationDao;

	


	@Override
	public GenericDAO<Cancellation, Integer> getDAO() {
		return (GenericDAO<Cancellation, Integer>) cancellationDao;
	}
	
	public List<Cancellation> findAll () {
		List<Cancellation> cancellations = cancellationDao.findAll();
		
		return cancellations;	
		
	}

	public ResultDTO addCancellation(CancellationDTO cancellationDTO, RequestDTO requestDTO) {

		Cancellation cancellation = new Cancellation();

		cancellation.setCancellationId(cancellationDTO.getCancellationId());


		cancellation.setCancellationDate(cancellationDTO.getCancellationDate());


		cancellation.setRefundAmount(cancellationDTO.getRefundAmount());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		cancellation = cancellationDao.save(cancellation);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Cancellation> getAllCancellations(Pageable pageable) {
		return cancellationDao.findAll(pageable);
	}

	public Page<Cancellation> getAllCancellations(Specification<Cancellation> spec, Pageable pageable) {
		return cancellationDao.findAll(spec, pageable);
	}

	public ResponseEntity<CancellationPageDTO> getCancellations(CancellationSearchDTO cancellationSearchDTO) {
	
			Integer cancellationId = cancellationSearchDTO.getCancellationId(); 
    			String sortBy = cancellationSearchDTO.getSortBy();
			String sortOrder = cancellationSearchDTO.getSortOrder();
			String searchQuery = cancellationSearchDTO.getSearchQuery();
			Integer page = cancellationSearchDTO.getPage();
			Integer size = cancellationSearchDTO.getSize();

	        Specification<Cancellation> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, cancellationId, "cancellationId"); 
			
 			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<Cancellation> cancellations = this.getAllCancellations(spec, pageable);
		
		//System.out.println(String.valueOf(cancellations.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(cancellations.getTotalPages()));
		
		List<Cancellation> cancellationsList = cancellations.getContent();
		
		CancellationConvertCriteriaDTO convertCriteria = new CancellationConvertCriteriaDTO();
		List<CancellationDTO> cancellationDTOs = this.convertCancellationsToCancellationDTOs(cancellationsList,convertCriteria);
		
		CancellationPageDTO cancellationPageDTO = new CancellationPageDTO();
		cancellationPageDTO.setCancellations(cancellationDTOs);
		cancellationPageDTO.setTotalElements(cancellations.getTotalElements());
		return ResponseEntity.ok(cancellationPageDTO);
	}

	public List<CancellationDTO> convertCancellationsToCancellationDTOs(List<Cancellation> cancellations, CancellationConvertCriteriaDTO convertCriteria) {
		
		List<CancellationDTO> cancellationDTOs = new ArrayList<CancellationDTO>();
		
		for (Cancellation cancellation : cancellations) {
			cancellationDTOs.add(convertCancellationToCancellationDTO(cancellation,convertCriteria));
		}
		
		return cancellationDTOs;

	}
	
	public CancellationDTO convertCancellationToCancellationDTO(Cancellation cancellation, CancellationConvertCriteriaDTO convertCriteria) {
		
		CancellationDTO cancellationDTO = new CancellationDTO();
		
		cancellationDTO.setCancellationId(cancellation.getCancellationId());

	
		cancellationDTO.setCancellationDate(cancellation.getCancellationDate());

	
		cancellationDTO.setRefundAmount(cancellation.getRefundAmount());

	

		
		return cancellationDTO;
	}

	public ResultDTO updateCancellation(CancellationDTO cancellationDTO, RequestDTO requestDTO) {
		
		Cancellation cancellation = cancellationDao.getById(cancellationDTO.getCancellationId());

		cancellation.setCancellationId(ControllerUtils.setValue(cancellation.getCancellationId(), cancellationDTO.getCancellationId()));

		cancellation.setCancellationDate(ControllerUtils.setValue(cancellation.getCancellationDate(), cancellationDTO.getCancellationDate()));

		cancellation.setRefundAmount(ControllerUtils.setValue(cancellation.getRefundAmount(), cancellationDTO.getRefundAmount()));



        cancellation = cancellationDao.save(cancellation);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CancellationDTO getCancellationDTOById(Integer cancellationId) {
	
		Cancellation cancellation = cancellationDao.getById(cancellationId);
			
		
		CancellationConvertCriteriaDTO convertCriteria = new CancellationConvertCriteriaDTO();
		return(this.convertCancellationToCancellationDTO(cancellation,convertCriteria));
	}







}
