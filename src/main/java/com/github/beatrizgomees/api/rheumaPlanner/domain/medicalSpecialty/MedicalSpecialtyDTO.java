package com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty;

import io.smallrye.common.constraint.NotNull;

import java.util.UUID;


public record MedicalSpecialtyDTO(
        UUID id,
        @NotNull
        String name,
        String description) {
}
