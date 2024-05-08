package com.github.beatrizgomees.api.rheumaPlanner.domain.doctor;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;

import java.util.UUID;

public record DoctorRequest (UUID id, String name, String lastname, String description, MedicalSpecialty medicalSpecialty,
                             String phoneNumber){
}
