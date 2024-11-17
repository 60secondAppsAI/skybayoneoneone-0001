package com.skybayoneoneone.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="cancellations")
@Getter @Setter @NoArgsConstructor
public class Cancellation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="cancellation_id")
	private Integer cancellationId;
    
  	@Column(name="cancellation_date")
	private Date cancellationDate;
    
  	@Column(name="refund_amount")
	private double refundAmount;
    
	




}
