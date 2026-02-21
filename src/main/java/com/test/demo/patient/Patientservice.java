package com.test.demo.patient;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class Patientservice {
    private final PatientRepo repo;
    public Patientservice(PatientRepo repo){
        this.repo= repo;
    }
    public List<PatientEntity> getAllPatients(){
        return repo.findAll();
    }
    public PatientEntity getPatientById(long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
    }
    public PatientEntity create(@RequestBody PatientEntity patient){
        return repo.save(patient);
    }
}
