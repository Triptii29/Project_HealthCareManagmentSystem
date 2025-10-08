package com.example.managementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.managementSystem.entity.Doctor;
import com.example.managementSystem.entity.Patient;
import com.example.managementSystem.repository.DoctorRepository;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Page<Doctor> findDoctorsBySpecialization(String specialization, int page, int size) {
        return doctorRepository.findBySpecialization(specialization, PageRequest.of(page, size));
    }

	public Optional<Doctor> findByEmail(String email) {
		// TODO Auto-generated method stub
		return doctorRepository.findByEmail(email);
	}

	
}
