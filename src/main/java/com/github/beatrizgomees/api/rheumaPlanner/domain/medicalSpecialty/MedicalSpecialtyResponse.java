package com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty;

import java.util.UUID;

public record MedicalSpecialtyResponse(UUID id, String name, String description) {
}
