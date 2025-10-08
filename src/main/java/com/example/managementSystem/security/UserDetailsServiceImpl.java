package com.example.managementSystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.managementSystem.entity.Doctor;
import com.example.managementSystem.entity.Patient;
import com.example.managementSystem.repository.DoctorRepository;
import com.example.managementSystem.repository.PatientRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private DoctorRepository doctorRepo;
    @Autowired private PatientRepository patientRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor doc = doctorRepo.findByEmail(username).orElse(null);
        if (doc != null)
            return org.springframework.security.core.userdetails.User.withUsername(doc.getEmail())
                .password(doc.getPassword()).roles("DOCTOR").build();
        Patient pat = patientRepo.findByEmail(username).orElse(null);
        if (pat != null)
            return org.springframework.security.core.userdetails.User.withUsername(pat.getEmail())
                .password(pat.getPassword()).roles("PATIENT").build();
        throw new UsernameNotFoundException("User not found");
    }
}
