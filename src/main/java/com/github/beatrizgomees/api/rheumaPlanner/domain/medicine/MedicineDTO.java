package com.github.beatrizgomees.api.rheumaPlanner.domain.medicine;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record MedicineDTO(
        UUID id,
        String name,
        String description,
        int amount,
        LocalDateTime reminder,
        MedicalSpecialtyDTO medicalSpecialty
) {
}
