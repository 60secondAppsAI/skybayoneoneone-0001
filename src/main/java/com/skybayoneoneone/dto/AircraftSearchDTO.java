package com.skybayoneoneone.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AircraftSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer aircraftId;
	
	private String modelName;
	
	private Integer seatingCapacity;
	
	private String registrationNumber;
	
}
