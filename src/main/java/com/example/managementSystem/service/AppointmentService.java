package com.example.managementSystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.managementSystem.entity.Appointment;
import com.example.managementSystem.repository.AppointmentRepository;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Appointment appointment) throws IllegalArgumentException {
        if (appointment.getAppointmentTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment time must be in the future");
        }
        List<Appointment> existing = appointmentRepository.findByDoctorIdAndAppointmentTime(
                appointment.getDoctorId(), appointment.getAppointmentTime());
        if (!existing.isEmpty()) {
            throw new IllegalArgumentException("Double booking not allowed");
        }
        appointment.setStatus(Appointment.Status.SCHEDULED);
        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
             .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        appointment.setStatus(Appointment.Status.CANCELLED);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public Appointment addPrescription(Long appointmentId, String notes) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
             .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        appointment.setNotes(notes);
        appointment.setStatus(Appointment.Status.COMPLETED);
        return appointmentRepository.save(appointment);
    }
}
