package com.example.managementSystem.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.managementSystem.entity.Doctor;
import com.example.managementSystem.entity.Patient;
import com.example.managementSystem.service.DoctorService;
import com.example.managementSystem.service.PatientService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired private DoctorService doctorService;
    @Autowired private PatientService patientService;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private PasswordEncoder encoder;

    @PostMapping("/register/doctor")
    public ResponseEntity<?> registerDoctor(@RequestBody Doctor doctor) {
        doctor.setPassword(encoder.encode(doctor.getPassword()));
        doctorService.saveDoctor(doctor);
        return ResponseEntity.ok("Doctor registered");
    }
    @PostMapping("/register/patient")
    public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {
        patient.setPassword(encoder.encode(patient.getPassword()));
        patientService.savePatient(patient);
        return ResponseEntity.ok("Patient registered");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> creds) {
        String email = creds.get("email");
        String password = creds.get("password");
        Doctor doc = doctorService.findByEmail(email).orElse(null);
        if (doc != null && encoder.matches(password, doc.getPassword())) {
            String token = jwtUtil.generateToken(email, "DOCTOR");
            return ResponseEntity.ok(Map.of("token",token,"role","DOCTOR"));
        }
        Patient pat = patientService.findByEmail(email).orElse(null);
        if (pat != null && encoder.matches(password, pat.getPassword())) {
            String token = jwtUtil.generateToken(email, "PATIENT");
            return ResponseEntity.ok(Map.of("token",token,"role","PATIENT"));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
