package com.skybayoneoneone.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class DelayNoticeDTO {

	private Integer delayNoticeId;

	private Date delayTime;

	private String reason;






}