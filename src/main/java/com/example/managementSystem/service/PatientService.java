package com.example.managementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.managementSystem.entity.Patient;
import com.example.managementSystem.repository.*;
import com.example.managementSystem.repository.*;

@Service
public class PatientService {
	
    @Autowired
	public PatientRepository patientRepository;
    
    public Patient savePatient(Patient patient) { return patientRepository.save(patient); }
    public Optional<Patient> findByEmail(String email) { return patientRepository.findByEmail(email); }
    public Optional<Patient> findById(Long id) { return patientRepository.findById(id); }
}
