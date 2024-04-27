package com.github.beatrizgomees.api.rheumaPlanner.doctor.dto;

import com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.entity.MedicalSpecialty;

import java.util.UUID;

public record DoctorRequest (UUID id, String name, String lastname, String description, MedicalSpecialty medicalSpecialty,
                             String phoneNumber){
}
