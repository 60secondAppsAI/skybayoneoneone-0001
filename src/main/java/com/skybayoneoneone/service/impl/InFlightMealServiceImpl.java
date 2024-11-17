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
import com.skybayoneoneone.dao.InFlightMealDAO;
import com.skybayoneoneone.domain.InFlightMeal;
import com.skybayoneoneone.dto.InFlightMealDTO;
import com.skybayoneoneone.dto.InFlightMealSearchDTO;
import com.skybayoneoneone.dto.InFlightMealPageDTO;
import com.skybayoneoneone.dto.InFlightMealConvertCriteriaDTO;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import com.skybayoneoneone.service.InFlightMealService;
import com.skybayoneoneone.util.ControllerUtils;





@Service
public class InFlightMealServiceImpl extends GenericServiceImpl<InFlightMeal, Integer> implements InFlightMealService {

    private final static Logger logger = LoggerFactory.getLogger(InFlightMealServiceImpl.class);

	@Autowired
	InFlightMealDAO inFlightMealDao;

	


	@Override
	public GenericDAO<InFlightMeal, Integer> getDAO() {
		return (GenericDAO<InFlightMeal, Integer>) inFlightMealDao;
	}
	
	public List<InFlightMeal> findAll () {
		List<InFlightMeal> inFlightMeals = inFlightMealDao.findAll();
		
		return inFlightMeals;	
		
	}

	public ResultDTO addInFlightMeal(InFlightMealDTO inFlightMealDTO, RequestDTO requestDTO) {

		InFlightMeal inFlightMeal = new InFlightMeal();

		inFlightMeal.setInFlightMealId(inFlightMealDTO.getInFlightMealId());


		inFlightMeal.setMealType(inFlightMealDTO.getMealType());


		inFlightMeal.setDietaryRestrictions(inFlightMealDTO.getDietaryRestrictions());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		inFlightMeal = inFlightMealDao.save(inFlightMeal);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<InFlightMeal> getAllInFlightMeals(Pageable pageable) {
		return inFlightMealDao.findAll(pageable);
	}

	public Page<InFlightMeal> getAllInFlightMeals(Specification<InFlightMeal> spec, Pageable pageable) {
		return inFlightMealDao.findAll(spec, pageable);
	}

	public ResponseEntity<InFlightMealPageDTO> getInFlightMeals(InFlightMealSearchDTO inFlightMealSearchDTO) {
	
			Integer inFlightMealId = inFlightMealSearchDTO.getInFlightMealId(); 
 			String mealType = inFlightMealSearchDTO.getMealType(); 
 			String dietaryRestrictions = inFlightMealSearchDTO.getDietaryRestrictions(); 
 			String sortBy = inFlightMealSearchDTO.getSortBy();
			String sortOrder = inFlightMealSearchDTO.getSortOrder();
			String searchQuery = inFlightMealSearchDTO.getSearchQuery();
			Integer page = inFlightMealSearchDTO.getPage();
			Integer size = inFlightMealSearchDTO.getSize();

	        Specification<InFlightMeal> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, inFlightMealId, "inFlightMealId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, mealType, "mealType"); 
			
			spec = ControllerUtils.andIfNecessary(spec, dietaryRestrictions, "dietaryRestrictions"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("mealType")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("dietaryRestrictions")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<InFlightMeal> inFlightMeals = this.getAllInFlightMeals(spec, pageable);
		
		//System.out.println(String.valueOf(inFlightMeals.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(inFlightMeals.getTotalPages()));
		
		List<InFlightMeal> inFlightMealsList = inFlightMeals.getContent();
		
		InFlightMealConvertCriteriaDTO convertCriteria = new InFlightMealConvertCriteriaDTO();
		List<InFlightMealDTO> inFlightMealDTOs = this.convertInFlightMealsToInFlightMealDTOs(inFlightMealsList,convertCriteria);
		
		InFlightMealPageDTO inFlightMealPageDTO = new InFlightMealPageDTO();
		inFlightMealPageDTO.setInFlightMeals(inFlightMealDTOs);
		inFlightMealPageDTO.setTotalElements(inFlightMeals.getTotalElements());
		return ResponseEntity.ok(inFlightMealPageDTO);
	}

	public List<InFlightMealDTO> convertInFlightMealsToInFlightMealDTOs(List<InFlightMeal> inFlightMeals, InFlightMealConvertCriteriaDTO convertCriteria) {
		
		List<InFlightMealDTO> inFlightMealDTOs = new ArrayList<InFlightMealDTO>();
		
		for (InFlightMeal inFlightMeal : inFlightMeals) {
			inFlightMealDTOs.add(convertInFlightMealToInFlightMealDTO(inFlightMeal,convertCriteria));
		}
		
		return inFlightMealDTOs;

	}
	
	public InFlightMealDTO convertInFlightMealToInFlightMealDTO(InFlightMeal inFlightMeal, InFlightMealConvertCriteriaDTO convertCriteria) {
		
		InFlightMealDTO inFlightMealDTO = new InFlightMealDTO();
		
		inFlightMealDTO.setInFlightMealId(inFlightMeal.getInFlightMealId());

	
		inFlightMealDTO.setMealType(inFlightMeal.getMealType());

	
		inFlightMealDTO.setDietaryRestrictions(inFlightMeal.getDietaryRestrictions());

	

		
		return inFlightMealDTO;
	}

	public ResultDTO updateInFlightMeal(InFlightMealDTO inFlightMealDTO, RequestDTO requestDTO) {
		
		InFlightMeal inFlightMeal = inFlightMealDao.getById(inFlightMealDTO.getInFlightMealId());

		inFlightMeal.setInFlightMealId(ControllerUtils.setValue(inFlightMeal.getInFlightMealId(), inFlightMealDTO.getInFlightMealId()));

		inFlightMeal.setMealType(ControllerUtils.setValue(inFlightMeal.getMealType(), inFlightMealDTO.getMealType()));

		inFlightMeal.setDietaryRestrictions(ControllerUtils.setValue(inFlightMeal.getDietaryRestrictions(), inFlightMealDTO.getDietaryRestrictions()));



        inFlightMeal = inFlightMealDao.save(inFlightMeal);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public InFlightMealDTO getInFlightMealDTOById(Integer inFlightMealId) {
	
		InFlightMeal inFlightMeal = inFlightMealDao.getById(inFlightMealId);
			
		
		InFlightMealConvertCriteriaDTO convertCriteria = new InFlightMealConvertCriteriaDTO();
		return(this.convertInFlightMealToInFlightMealDTO(inFlightMeal,convertCriteria));
	}







}
