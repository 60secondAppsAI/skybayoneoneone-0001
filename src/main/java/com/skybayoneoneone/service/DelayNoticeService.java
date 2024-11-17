package com.skybayoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayoneoneone.domain.DelayNotice;
import com.skybayoneoneone.dto.DelayNoticeDTO;
import com.skybayoneoneone.dto.DelayNoticeSearchDTO;
import com.skybayoneoneone.dto.DelayNoticePageDTO;
import com.skybayoneoneone.dto.DelayNoticeConvertCriteriaDTO;
import com.skybayoneoneone.service.GenericService;
import com.skybayoneoneone.dto.common.RequestDTO;
import com.skybayoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DelayNoticeService extends GenericService<DelayNotice, Integer> {

	List<DelayNotice> findAll();

	ResultDTO addDelayNotice(DelayNoticeDTO delayNoticeDTO, RequestDTO requestDTO);

	ResultDTO updateDelayNotice(DelayNoticeDTO delayNoticeDTO, RequestDTO requestDTO);

    Page<DelayNotice> getAllDelayNotices(Pageable pageable);

    Page<DelayNotice> getAllDelayNotices(Specification<DelayNotice> spec, Pageable pageable);

	ResponseEntity<DelayNoticePageDTO> getDelayNotices(DelayNoticeSearchDTO delayNoticeSearchDTO);
	
	List<DelayNoticeDTO> convertDelayNoticesToDelayNoticeDTOs(List<DelayNotice> delayNotices, DelayNoticeConvertCriteriaDTO convertCriteria);

	DelayNoticeDTO getDelayNoticeDTOById(Integer delayNoticeId);







}





