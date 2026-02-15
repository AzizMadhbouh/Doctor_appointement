package com.test.demo.doctors;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping
    public List<DoctorEntity> getAllDoctors() {
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorEntity getDoctor(@PathVariable Long id) {
        return service.getDoctorById(id);
    }

    @PostMapping
    public DoctorEntity createDoctor(@RequestBody DoctorEntity doctor) {
        return service.create(doctor);
    }
}
