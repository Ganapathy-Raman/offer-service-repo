package com.rts.tap.model;

import java.time.LocalDateTime;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "client_tbl")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;

	@Column
	private String clientName;

	@Column
	private String clientPosition;

	@Column
	private String clientMobile;

	@Column
	private String clientEmail;

	@Column
	private String password;

	@Column
	private String clientStatus;
	
	@Column
    @Enumerated(EnumType.STRING)
    private ClientActivationStatus activationStatus;

	@Column
	private Date createdAt;

	@Column
	private String otp;

	@Column
	private LocalDateTime expirationTime;

	@ManyToOne
	@JoinColumn(name = "clientPartnerId")
	private Employee clientPartner;

	@ManyToOne
	private ClientOrganization clientOrganization;

	@OneToOne
	private ThirdPartyCredentitals thirdPartyCredentitals;

	@Column
	private String reason;

	public enum ClientActivationStatus {
	    ACTIVE,
	    INACTIVE;
	}

}
