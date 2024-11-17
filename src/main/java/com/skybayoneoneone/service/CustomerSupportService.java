package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.CustomerSupport;
import com.skybayoneoneone.dto.CustomerSupportDTO;
import com.skybayoneoneone.dto.CustomerSupportSearchDTO;
import com.skybayoneoneone.dto.CustomerSupportPageDTO;
import com.skybayoneoneone.dto.CustomerSupportConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CustomerSupportService extends GenericService<CustomerSupport, Integer> {

	List<CustomerSupport> findAll();

	ResultDTO addCustomerSupport(CustomerSupportDTO customerSupportDTO, RequestDTO requestDTO);

	ResultDTO updateCustomerSupport(CustomerSupportDTO customerSupportDTO, RequestDTO requestDTO);

    Page<CustomerSupport> getAllCustomerSupports(Pageable pageable);

    Page<CustomerSupport> getAllCustomerSupports(Specification<CustomerSupport> spec, Pageable pageable);

	ResponseEntity<CustomerSupportPageDTO> getCustomerSupports(CustomerSupportSearchDTO customerSupportSearchDTO);
	
	List<CustomerSupportDTO> convertCustomerSupportsToCustomerSupportDTOs(List<CustomerSupport> customerSupports, CustomerSupportConvertCriteriaDTO convertCriteria);

	CustomerSupportDTO getCustomerSupportDTOById(Integer customerSupportId);







}





