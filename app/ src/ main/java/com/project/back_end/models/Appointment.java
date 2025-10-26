package com.project.back_end.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    private Patient patient;

    private LocalDateTime appointmentDate;
    private String reason;
    private String status; // e.g. Scheduled, Completed, Cancelled

    // Constructors
    public Appointment() {}

    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentDate, String reason, String status) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.reason = reason;
        this.status = status;
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", doctor=" + (doctor != null ? doctor.getName() : "null") +
                ", patient=" + (patient != null ? patient.getName() : "null") +
                ", appointmentDate=" + appointmentDate +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
