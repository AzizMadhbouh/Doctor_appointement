package com.test.demo.patient;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PatientRepo extends JpaRepository<PatientEntity, Long> {
    
}
