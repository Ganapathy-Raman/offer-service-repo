package com.rts.tap.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "employee_email", unique = true, nullable = false)
	private String employeeEmail;

	@Column(name = "employee_name", nullable = false)
	private String employeeName;

	@Column(name = "work_location")
	private String workLocation;

	@Enumerated(EnumType.STRING)
	@Column(name = "employee_status")
	private EmploymentStatus employeeStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "businessunit_id")
	private BusinessUnit businessUnit;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@Column(name = "manager_id")
	private Long managerId; // Instead of directly referencing the manager as an Employee, store the
							// manager's employeeId
	
	@Column(name = "candidate_id")
	private Long candidateId;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Ensure the proper cascade type
    @JoinColumn(name = "personal_info_id", referencedColumnName = "personal_info_id") 
	private PersonalInfo personalInfo;


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Experience> experience;

	@OneToMany
	private List<FamilyDetails> familyDetails;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Qualification> qualification;

	@OneToMany
	private List<Documents> documents;

	@PrePersist
	protected void onCreate() {
		createdDate = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = LocalDateTime.now();
	}

	public enum EmploymentStatus {
		ACTIVE, INACTIVE
	}
}
