package com.github.beatrizgomees.api.rheumaPlanner.domain.medicine;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;

import java.time.LocalDateTime;
import java.util.UUID;

public record MedicineResponse(
        UUID id,
        String name,
        String description,
        int amount,
        LocalDateTime reminder,
        MedicalSpecialty medicalSpecialty){
}
