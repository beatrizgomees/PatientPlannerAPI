package com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty;

import io.smallrye.common.constraint.NotNull;


public record MedicalSpecialtyDTO(
        @NotNull
        String name,
        String description) {
}
