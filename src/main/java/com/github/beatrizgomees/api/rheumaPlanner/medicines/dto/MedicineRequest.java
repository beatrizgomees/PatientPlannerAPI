package com.github.beatrizgomees.api.rheumaPlanner.medicines.dto;

import com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.entity.MedicalSpecialty;

import java.time.LocalDateTime;

public record MedicineRequest(String name, int amount, LocalDateTime reminder, String description, MedicalSpecialty medicalSpecialty) {
}
