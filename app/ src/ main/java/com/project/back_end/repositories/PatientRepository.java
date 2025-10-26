package com.project.back_end.repositories;

import com.project.back_end.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Lấy bệnh nhân theo email
    Optional<Patient> findByEmail(String email);

    // Lấy bệnh nhân theo email hoặc số điện thoại
    Optional<Patient> findByEmailOrPhone(String email, String phone);
}
