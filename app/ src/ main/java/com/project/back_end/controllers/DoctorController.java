package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    /**
     * Endpoint: GET /api/doctors/available?speciality=Cardiology&date=2025-10-30
     * -> Returns list of doctors available on given date and speciality
     */
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableDoctors(
            @RequestHeader("Authorization") String token,
            @RequestParam String speciality,
            @RequestParam String date
    ) {
        try {
            // Token validation
            if (!tokenService.validateToken(token)) {
                return ResponseEntity.status(401).body("Invalid or expired token");
            }

            // Fetch available doctors
            List<Doctor> availableDoctors = doctorService.getAvailableDoctors(speciality, date);
            if (availableDoctors.isEmpty()) {
                return ResponseEntity.ok("No doctors available for this speciality and date");
            }

            return ResponseEntity.ok(availableDoctors);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving doctor availability: " + e.getMessage());
        }
    }
}
