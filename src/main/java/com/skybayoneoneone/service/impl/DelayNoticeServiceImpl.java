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
import com.skybayoneoneone.dao.DelayNoticeDAO;
import com.skybayoneoneone.domain.DelayNotice;
import com.skybayoneoneone.dto.DelayNoticeDTO;
import com.skybayoneoneone.dto.DelayNoticeSearchDTO;
import com.skybayoneoneone.dto.DelayNoticePageDTO;
import com.skybayoneoneone.dto.DelayNoticeConvertCriteriaDTO;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import com.skybayoneoneone.service.DelayNoticeService;
import com.skybayoneoneone.util.ControllerUtils;





@Service
public class DelayNoticeServiceImpl extends GenericServiceImpl<DelayNotice, Integer> implements DelayNoticeService {

    private final static Logger logger = LoggerFactory.getLogger(DelayNoticeServiceImpl.class);

	@Autowired
	DelayNoticeDAO delayNoticeDao;

	


	@Override
	public GenericDAO<DelayNotice, Integer> getDAO() {
		return (GenericDAO<DelayNotice, Integer>) delayNoticeDao;
	}
	
	public List<DelayNotice> findAll () {
		List<DelayNotice> delayNotices = delayNoticeDao.findAll();
		
		return delayNotices;	
		
	}

	public ResultDTO addDelayNotice(DelayNoticeDTO delayNoticeDTO, RequestDTO requestDTO) {

		DelayNotice delayNotice = new DelayNotice();

		delayNotice.setDelayNoticeId(delayNoticeDTO.getDelayNoticeId());


		delayNotice.setDelayTime(delayNoticeDTO.getDelayTime());


		delayNotice.setReason(delayNoticeDTO.getReason());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		delayNotice = delayNoticeDao.save(delayNotice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<DelayNotice> getAllDelayNotices(Pageable pageable) {
		return delayNoticeDao.findAll(pageable);
	}

	public Page<DelayNotice> getAllDelayNotices(Specification<DelayNotice> spec, Pageable pageable) {
		return delayNoticeDao.findAll(spec, pageable);
	}

	public ResponseEntity<DelayNoticePageDTO> getDelayNotices(DelayNoticeSearchDTO delayNoticeSearchDTO) {
	
			Integer delayNoticeId = delayNoticeSearchDTO.getDelayNoticeId(); 
   			String reason = delayNoticeSearchDTO.getReason(); 
 			String sortBy = delayNoticeSearchDTO.getSortBy();
			String sortOrder = delayNoticeSearchDTO.getSortOrder();
			String searchQuery = delayNoticeSearchDTO.getSearchQuery();
			Integer page = delayNoticeSearchDTO.getPage();
			Integer size = delayNoticeSearchDTO.getSize();

	        Specification<DelayNotice> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, delayNoticeId, "delayNoticeId"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, reason, "reason"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("reason")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<DelayNotice> delayNotices = this.getAllDelayNotices(spec, pageable);
		
		//System.out.println(String.valueOf(delayNotices.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(delayNotices.getTotalPages()));
		
		List<DelayNotice> delayNoticesList = delayNotices.getContent();
		
		DelayNoticeConvertCriteriaDTO convertCriteria = new DelayNoticeConvertCriteriaDTO();
		List<DelayNoticeDTO> delayNoticeDTOs = this.convertDelayNoticesToDelayNoticeDTOs(delayNoticesList,convertCriteria);
		
		DelayNoticePageDTO delayNoticePageDTO = new DelayNoticePageDTO();
		delayNoticePageDTO.setDelayNotices(delayNoticeDTOs);
		delayNoticePageDTO.setTotalElements(delayNotices.getTotalElements());
		return ResponseEntity.ok(delayNoticePageDTO);
	}

	public List<DelayNoticeDTO> convertDelayNoticesToDelayNoticeDTOs(List<DelayNotice> delayNotices, DelayNoticeConvertCriteriaDTO convertCriteria) {
		
		List<DelayNoticeDTO> delayNoticeDTOs = new ArrayList<DelayNoticeDTO>();
		
		for (DelayNotice delayNotice : delayNotices) {
			delayNoticeDTOs.add(convertDelayNoticeToDelayNoticeDTO(delayNotice,convertCriteria));
		}
		
		return delayNoticeDTOs;

	}
	
	public DelayNoticeDTO convertDelayNoticeToDelayNoticeDTO(DelayNotice delayNotice, DelayNoticeConvertCriteriaDTO convertCriteria) {
		
		DelayNoticeDTO delayNoticeDTO = new DelayNoticeDTO();
		
		delayNoticeDTO.setDelayNoticeId(delayNotice.getDelayNoticeId());

	
		delayNoticeDTO.setDelayTime(delayNotice.getDelayTime());

	
		delayNoticeDTO.setReason(delayNotice.getReason());

	

		
		return delayNoticeDTO;
	}

	public ResultDTO updateDelayNotice(DelayNoticeDTO delayNoticeDTO, RequestDTO requestDTO) {
		
		DelayNotice delayNotice = delayNoticeDao.getById(delayNoticeDTO.getDelayNoticeId());

		delayNotice.setDelayNoticeId(ControllerUtils.setValue(delayNotice.getDelayNoticeId(), delayNoticeDTO.getDelayNoticeId()));

		delayNotice.setDelayTime(ControllerUtils.setValue(delayNotice.getDelayTime(), delayNoticeDTO.getDelayTime()));

		delayNotice.setReason(ControllerUtils.setValue(delayNotice.getReason(), delayNoticeDTO.getReason()));



        delayNotice = delayNoticeDao.save(delayNotice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DelayNoticeDTO getDelayNoticeDTOById(Integer delayNoticeId) {
	
		DelayNotice delayNotice = delayNoticeDao.getById(delayNoticeId);
			
		
		DelayNoticeConvertCriteriaDTO convertCriteria = new DelayNoticeConvertCriteriaDTO();
		return(this.convertDelayNoticeToDelayNoticeDTO(delayNotice,convertCriteria));
	}







}
