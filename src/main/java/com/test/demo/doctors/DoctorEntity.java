package com.test.demo.doctors;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String speciality;
    private String adresse;

    public DoctorEntity() {
    }

    public DoctorEntity(String name, String speciality, String adresse) {
        this.name = name;
        this.speciality = speciality;
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
