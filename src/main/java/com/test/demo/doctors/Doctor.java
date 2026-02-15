package com.test.demo.doctors;

public class Doctor {
    Long id;
    String name;
    String speciality;
    String adresse;

    public Doctor() {
    }

    public Doctor(Long id, String name, String speciality, String adresse) {
        this.id = id;
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
