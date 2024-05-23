package com.github.beatrizgomees.api.rheumaPlanner.domain.medicine;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;

import java.time.LocalDateTime;
import java.util.UUID;

public record MedicineRequest(UUID id, String name, int amount, LocalDateTime reminder, String description, MedicalSpecialty medicalSpecialty) {
}
