package com.rts.tap.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Login_Credentials")
public class LoginCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

	@Column(name = "password_hash", nullable = false)
	private String passwordHash;
	
	@Column(name = "ispassword_change")
	private String isPasswordChange = "NO";
    
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "failed_attempts", nullable = false, columnDefinition = "int default 0")
    private int failedAttempts = 0; // Track the number of failed login attempts

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "lockout_time")
    private LocalDateTime lockoutTime; // Track when the lockout ends

    @Column(name = "lockout_count", nullable = false, columnDefinition = "int default 0")
    private int lockoutCount = 0; // Track the number of times the account has been locked

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employee;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
