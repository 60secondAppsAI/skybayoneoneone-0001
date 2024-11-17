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
import com.skybayoneoneone.dao.CheckInDAO;
import com.skybayoneoneone.domain.CheckIn;
import com.skybayoneoneone.dto.CheckInDTO;
import com.skybayoneoneone.dto.CheckInSearchDTO;
import com.skybayoneoneone.dto.CheckInPageDTO;
import com.skybayoneoneone.dto.CheckInConvertCriteriaDTO;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import com.skybayoneoneone.service.CheckInService;
import com.skybayoneoneone.util.ControllerUtils;





@Service
public class CheckInServiceImpl extends GenericServiceImpl<CheckIn, Integer> implements CheckInService {

    private final static Logger logger = LoggerFactory.getLogger(CheckInServiceImpl.class);

	@Autowired
	CheckInDAO checkInDao;

	


	@Override
	public GenericDAO<CheckIn, Integer> getDAO() {
		return (GenericDAO<CheckIn, Integer>) checkInDao;
	}
	
	public List<CheckIn> findAll () {
		List<CheckIn> checkIns = checkInDao.findAll();
		
		return checkIns;	
		
	}

	public ResultDTO addCheckIn(CheckInDTO checkInDTO, RequestDTO requestDTO) {

		CheckIn checkIn = new CheckIn();

		checkIn.setCheckInId(checkInDTO.getCheckInId());


		checkIn.setCheckInDate(checkInDTO.getCheckInDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		checkIn = checkInDao.save(checkIn);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CheckIn> getAllCheckIns(Pageable pageable) {
		return checkInDao.findAll(pageable);
	}

	public Page<CheckIn> getAllCheckIns(Specification<CheckIn> spec, Pageable pageable) {
		return checkInDao.findAll(spec, pageable);
	}

	public ResponseEntity<CheckInPageDTO> getCheckIns(CheckInSearchDTO checkInSearchDTO) {
	
			Integer checkInId = checkInSearchDTO.getCheckInId(); 
   			String sortBy = checkInSearchDTO.getSortBy();
			String sortOrder = checkInSearchDTO.getSortOrder();
			String searchQuery = checkInSearchDTO.getSearchQuery();
			Integer page = checkInSearchDTO.getPage();
			Integer size = checkInSearchDTO.getSize();

	        Specification<CheckIn> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, checkInId, "checkInId"); 
			
 			

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

		Page<CheckIn> checkIns = this.getAllCheckIns(spec, pageable);
		
		//System.out.println(String.valueOf(checkIns.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(checkIns.getTotalPages()));
		
		List<CheckIn> checkInsList = checkIns.getContent();
		
		CheckInConvertCriteriaDTO convertCriteria = new CheckInConvertCriteriaDTO();
		List<CheckInDTO> checkInDTOs = this.convertCheckInsToCheckInDTOs(checkInsList,convertCriteria);
		
		CheckInPageDTO checkInPageDTO = new CheckInPageDTO();
		checkInPageDTO.setCheckIns(checkInDTOs);
		checkInPageDTO.setTotalElements(checkIns.getTotalElements());
		return ResponseEntity.ok(checkInPageDTO);
	}

	public List<CheckInDTO> convertCheckInsToCheckInDTOs(List<CheckIn> checkIns, CheckInConvertCriteriaDTO convertCriteria) {
		
		List<CheckInDTO> checkInDTOs = new ArrayList<CheckInDTO>();
		
		for (CheckIn checkIn : checkIns) {
			checkInDTOs.add(convertCheckInToCheckInDTO(checkIn,convertCriteria));
		}
		
		return checkInDTOs;

	}
	
	public CheckInDTO convertCheckInToCheckInDTO(CheckIn checkIn, CheckInConvertCriteriaDTO convertCriteria) {
		
		CheckInDTO checkInDTO = new CheckInDTO();
		
		checkInDTO.setCheckInId(checkIn.getCheckInId());

	
		checkInDTO.setCheckInDate(checkIn.getCheckInDate());

	

		
		return checkInDTO;
	}

	public ResultDTO updateCheckIn(CheckInDTO checkInDTO, RequestDTO requestDTO) {
		
		CheckIn checkIn = checkInDao.getById(checkInDTO.getCheckInId());

		checkIn.setCheckInId(ControllerUtils.setValue(checkIn.getCheckInId(), checkInDTO.getCheckInId()));

		checkIn.setCheckInDate(ControllerUtils.setValue(checkIn.getCheckInDate(), checkInDTO.getCheckInDate()));



        checkIn = checkInDao.save(checkIn);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CheckInDTO getCheckInDTOById(Integer checkInId) {
	
		CheckIn checkIn = checkInDao.getById(checkInId);
			
		
		CheckInConvertCriteriaDTO convertCriteria = new CheckInConvertCriteriaDTO();
		return(this.convertCheckInToCheckInDTO(checkIn,convertCriteria));
	}







}
