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
import com.skybayoneoneone.dao.CustomerSupportDAO;
import com.skybayoneoneone.domain.CustomerSupport;
import com.skybayoneoneone.dto.CustomerSupportDTO;
import com.skybayoneoneone.dto.CustomerSupportSearchDTO;
import com.skybayoneoneone.dto.CustomerSupportPageDTO;
import com.skybayoneoneone.dto.CustomerSupportConvertCriteriaDTO;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import com.skybayoneoneone.service.CustomerSupportService;
import com.skybayoneoneone.util.ControllerUtils;





@Service
public class CustomerSupportServiceImpl extends GenericServiceImpl<CustomerSupport, Integer> implements CustomerSupportService {

    private final static Logger logger = LoggerFactory.getLogger(CustomerSupportServiceImpl.class);

	@Autowired
	CustomerSupportDAO customerSupportDao;

	


	@Override
	public GenericDAO<CustomerSupport, Integer> getDAO() {
		return (GenericDAO<CustomerSupport, Integer>) customerSupportDao;
	}
	
	public List<CustomerSupport> findAll () {
		List<CustomerSupport> customerSupports = customerSupportDao.findAll();
		
		return customerSupports;	
		
	}

	public ResultDTO addCustomerSupport(CustomerSupportDTO customerSupportDTO, RequestDTO requestDTO) {

		CustomerSupport customerSupport = new CustomerSupport();

		customerSupport.setCustomerSupportId(customerSupportDTO.getCustomerSupportId());


		customerSupport.setName(customerSupportDTO.getName());


		customerSupport.setSupportId(customerSupportDTO.getSupportId());


		customerSupport.setContactEmail(customerSupportDTO.getContactEmail());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		customerSupport = customerSupportDao.save(customerSupport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CustomerSupport> getAllCustomerSupports(Pageable pageable) {
		return customerSupportDao.findAll(pageable);
	}

	public Page<CustomerSupport> getAllCustomerSupports(Specification<CustomerSupport> spec, Pageable pageable) {
		return customerSupportDao.findAll(spec, pageable);
	}

	public ResponseEntity<CustomerSupportPageDTO> getCustomerSupports(CustomerSupportSearchDTO customerSupportSearchDTO) {
	
			Integer customerSupportId = customerSupportSearchDTO.getCustomerSupportId(); 
 			String name = customerSupportSearchDTO.getName(); 
 			String supportId = customerSupportSearchDTO.getSupportId(); 
 			String contactEmail = customerSupportSearchDTO.getContactEmail(); 
 			String sortBy = customerSupportSearchDTO.getSortBy();
			String sortOrder = customerSupportSearchDTO.getSortOrder();
			String searchQuery = customerSupportSearchDTO.getSearchQuery();
			Integer page = customerSupportSearchDTO.getPage();
			Integer size = customerSupportSearchDTO.getSize();

	        Specification<CustomerSupport> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, customerSupportId, "customerSupportId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, supportId, "supportId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactEmail, "contactEmail"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("supportId")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contactEmail")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<CustomerSupport> customerSupports = this.getAllCustomerSupports(spec, pageable);
		
		//System.out.println(String.valueOf(customerSupports.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(customerSupports.getTotalPages()));
		
		List<CustomerSupport> customerSupportsList = customerSupports.getContent();
		
		CustomerSupportConvertCriteriaDTO convertCriteria = new CustomerSupportConvertCriteriaDTO();
		List<CustomerSupportDTO> customerSupportDTOs = this.convertCustomerSupportsToCustomerSupportDTOs(customerSupportsList,convertCriteria);
		
		CustomerSupportPageDTO customerSupportPageDTO = new CustomerSupportPageDTO();
		customerSupportPageDTO.setCustomerSupports(customerSupportDTOs);
		customerSupportPageDTO.setTotalElements(customerSupports.getTotalElements());
		return ResponseEntity.ok(customerSupportPageDTO);
	}

	public List<CustomerSupportDTO> convertCustomerSupportsToCustomerSupportDTOs(List<CustomerSupport> customerSupports, CustomerSupportConvertCriteriaDTO convertCriteria) {
		
		List<CustomerSupportDTO> customerSupportDTOs = new ArrayList<CustomerSupportDTO>();
		
		for (CustomerSupport customerSupport : customerSupports) {
			customerSupportDTOs.add(convertCustomerSupportToCustomerSupportDTO(customerSupport,convertCriteria));
		}
		
		return customerSupportDTOs;

	}
	
	public CustomerSupportDTO convertCustomerSupportToCustomerSupportDTO(CustomerSupport customerSupport, CustomerSupportConvertCriteriaDTO convertCriteria) {
		
		CustomerSupportDTO customerSupportDTO = new CustomerSupportDTO();
		
		customerSupportDTO.setCustomerSupportId(customerSupport.getCustomerSupportId());

	
		customerSupportDTO.setName(customerSupport.getName());

	
		customerSupportDTO.setSupportId(customerSupport.getSupportId());

	
		customerSupportDTO.setContactEmail(customerSupport.getContactEmail());

	

		
		return customerSupportDTO;
	}

	public ResultDTO updateCustomerSupport(CustomerSupportDTO customerSupportDTO, RequestDTO requestDTO) {
		
		CustomerSupport customerSupport = customerSupportDao.getById(customerSupportDTO.getCustomerSupportId());

		customerSupport.setCustomerSupportId(ControllerUtils.setValue(customerSupport.getCustomerSupportId(), customerSupportDTO.getCustomerSupportId()));

		customerSupport.setName(ControllerUtils.setValue(customerSupport.getName(), customerSupportDTO.getName()));

		customerSupport.setSupportId(ControllerUtils.setValue(customerSupport.getSupportId(), customerSupportDTO.getSupportId()));

		customerSupport.setContactEmail(ControllerUtils.setValue(customerSupport.getContactEmail(), customerSupportDTO.getContactEmail()));



        customerSupport = customerSupportDao.save(customerSupport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CustomerSupportDTO getCustomerSupportDTOById(Integer customerSupportId) {
	
		CustomerSupport customerSupport = customerSupportDao.getById(customerSupportId);
			
		
		CustomerSupportConvertCriteriaDTO convertCriteria = new CustomerSupportConvertCriteriaDTO();
		return(this.convertCustomerSupportToCustomerSupportDTO(customerSupport,convertCriteria));
	}







}
