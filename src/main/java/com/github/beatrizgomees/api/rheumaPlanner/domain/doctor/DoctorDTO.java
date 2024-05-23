package com.github.beatrizgomees.api.rheumaPlanner.domain.doctor;


import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;
import io.smallrye.common.constraint.NotNull;
import org.hibernate.annotations.BatchSize;

import java.io.Serializable;
import java.util.UUID;

public record DoctorDTO (UUID id,
                        String firstName,
                        String lastName,
                        @NotNull
                        MedicalSpecialty medicalSpecialty,
                        @BatchSize(size = 11)
                        String phoneNumber,
                        String description)
        implements Serializable{}