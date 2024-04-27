package com.github.beatrizgomees.api.rheumaPlanner.doctor.entity;

import com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.entity.MedicalSpecialty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "medical_specialty_id")
    private MedicalSpecialty medicalSpecialty;

    private String description;

    private String phoneNumber;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public MedicalSpecialty getMedicalSpecialty() {
        return medicalSpecialty;
    }

    public void setMedicalSpecialty(MedicalSpecialty medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", medicalSpecialty='" + medicalSpecialty + '\'' +
                ", description='" + description + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
