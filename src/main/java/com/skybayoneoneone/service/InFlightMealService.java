package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.InFlightMeal;
import com.skybayoneoneone.dto.InFlightMealDTO;
import com.skybayoneoneone.dto.InFlightMealSearchDTO;
import com.skybayoneoneone.dto.InFlightMealPageDTO;
import com.skybayoneoneone.dto.InFlightMealConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InFlightMealService extends GenericService<InFlightMeal, Integer> {

	List<InFlightMeal> findAll();

	ResultDTO addInFlightMeal(InFlightMealDTO inFlightMealDTO, RequestDTO requestDTO);

	ResultDTO updateInFlightMeal(InFlightMealDTO inFlightMealDTO, RequestDTO requestDTO);

    Page<InFlightMeal> getAllInFlightMeals(Pageable pageable);

    Page<InFlightMeal> getAllInFlightMeals(Specification<InFlightMeal> spec, Pageable pageable);

	ResponseEntity<InFlightMealPageDTO> getInFlightMeals(InFlightMealSearchDTO inFlightMealSearchDTO);
	
	List<InFlightMealDTO> convertInFlightMealsToInFlightMealDTOs(List<InFlightMeal> inFlightMeals, InFlightMealConvertCriteriaDTO convertCriteria);

	InFlightMealDTO getInFlightMealDTOById(Integer inFlightMealId);







}





