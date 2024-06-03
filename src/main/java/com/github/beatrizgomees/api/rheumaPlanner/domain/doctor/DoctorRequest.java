package com.github.beatrizgomees.api.rheumaPlanner.domain.doctor;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;

import java.util.UUID;

public record DoctorRequest (UUID id, String firstName, String lastname, String description, MedicalSpecialtyDTO medicalSpecialty,
                             String phoneNumber){
}
