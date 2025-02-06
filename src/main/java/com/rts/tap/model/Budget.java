package com.rts.tap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "budget")
public class Budget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long budgetId;
	@Column
	private double analyzedMinimumCTC;
	@Column
	private double analyzedMaximumCTC;

	@Column
	private double budgetForResource;

	@ManyToOne
	@JoinColumn(name = "requirementId")
	private Requirement requirement;

	@OneToOne
	@JoinColumn(name = "subRequirementId")
	private SubRequirements subRequirementId;

	@Column
	private String yearsOfExperience;
	
	@Column 
	private boolean isNegotiable;
	
}