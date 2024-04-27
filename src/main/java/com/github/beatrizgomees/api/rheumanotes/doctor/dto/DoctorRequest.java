package com.github.beatrizgomees.api.rheumanotes.doctor.dto;

import com.couchbase.client.core.deps.com.google.type.PhoneNumber;
import com.github.beatrizgomees.api.rheumanotes.medicalSpecialty.entity.MedicalSpecialty;

import java.time.LocalDateTime;
import java.util.UUID;

public record DoctorRequest (UUID id, String name, String lastname, String description, MedicalSpecialty medicalSpecialty,
                             String phoneNumber){
}
