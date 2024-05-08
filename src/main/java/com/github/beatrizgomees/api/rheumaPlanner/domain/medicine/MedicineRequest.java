package com.github.beatrizgomees.api.rheumaPlanner.domain.medicine;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;

import java.time.LocalDateTime;

public record MedicineRequest(String name, int amount, LocalDateTime reminder, String description, MedicalSpecialty medicalSpecialty) {
}
