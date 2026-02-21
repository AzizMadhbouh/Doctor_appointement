package com.test.demo.patient;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    public final Patientservice service;

    public PatientController(Patientservice service) {
        this.service = service;
    }

    @GetMapping
    List<PatientEntity> getAllPatients() {
        return service.getAllPatients();
    }

    @GetMapping("/{id}")
    PatientEntity getPatientById(@PathVariable Long id) {
        return service.getPatientById(id);
    }

    @PostMapping
    PatientEntity createPatient(@RequestBody PatientEntity patient) {
        return service.create(patient);
    }

}
