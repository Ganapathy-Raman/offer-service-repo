package com.rts.tap.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "organization_name", unique = true)
    private String organizationName;

    @Column(name = "organization_address")
    private String organizationAddress;

    @Column(name = "contact_person_name")
    private String contactPersonName;

    @Column(name = "contact_person_email")
    private String contactPersonEmail;

    @Column(name = "contact_person_phone")
    private String contactPersonPhone;

    @Column(name = "organization_websiteurl")
    private String organizationWebsiteUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "industry_type")
    private Industry industryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "organization_size")
    private OrganizationSize size;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "organization_establisheddate")
    private LocalDateTime organizationEstablishedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "organization_status")
    private Status organizationStatus;

    @Column(name = "created_date", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedDate;
    
    @Lob
    @Column(name = "organization_logo",length = 100000)
    private byte[] logo;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }

    public enum Industry {
        TECHNOLOGY, FINANCE, HEALTHCARE, EDUCATION, OTHER
    }

    public enum OrganizationSize {
        SMALL, MEDIUM, LARGE
    }

    public enum Status {
        ACTIVE, INACTIVE
    }

}
