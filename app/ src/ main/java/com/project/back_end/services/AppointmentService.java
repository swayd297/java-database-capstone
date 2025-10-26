package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.models.Doctor;
import com.project.back_end.models.Patient;
import com.project.back_end.repositories.AppointmentRepository;
import com.project.back_end.repositories.DoctorRepository;
import com.project.back_end.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Book a new appointment
     * @param doctorId - ID of the doctor
     * @param patientId - ID of the patient
     * @param appointmentTime - time of appointment
     * @return saved Appointment object
     */
    public Appointment bookAppointment(Long doctorId, Long patientId, LocalDate appointmentTime) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(appointmentTime.atStartOfDay());

        return appointmentRepository.save(appointment);
    }

    /**
     * Get all appointments for a doctor on a given date
     * @param doctorId - ID of the doctor
     * @param date - target date
     * @return List of appointments
     */
    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        LocalDate start = date.atStartOfDay().toLocalDate();
        LocalDate end = date.plusDays(1);
        return appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(doctorId, start.atStartOfDay(), end.atStartOfDay());
    }
}
