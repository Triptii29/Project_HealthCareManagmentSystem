package com.example.managementSystem.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	@ElementCollection
    private List<LocalDateTime> availableSlots;
	public Long getId() {
		return id;
	}
	
	
	// Getters and Setters
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<LocalDateTime> getAvailableSlots() {
		return availableSlots;
	}
	public void setAvailableSlots(List<LocalDateTime> availableSlots) {
		this.availableSlots = availableSlots;
	}

    
    
}
