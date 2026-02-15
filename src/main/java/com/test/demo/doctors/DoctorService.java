package com.test.demo.doctors;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepo repo;

    public DoctorService(DoctorRepo repo) {
        this.repo = repo;
    }

    public List<DoctorEntity> getAllDoctors() {
        return repo.findAll();
    }

    public DoctorEntity getDoctorById(long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public DoctorEntity create(DoctorEntity doctor) {
        return repo.save(doctor);
    }
}
