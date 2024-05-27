package com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty;

import java.util.UUID;

public record MedicalSpecialtyRequest(
       UUID id, String name, String description) {
}
