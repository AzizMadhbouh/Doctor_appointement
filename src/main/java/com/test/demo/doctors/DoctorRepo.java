package com.test.demo.doctors;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DoctorRepo extends JpaRepository<DoctorEntity, Long> {
    
}
