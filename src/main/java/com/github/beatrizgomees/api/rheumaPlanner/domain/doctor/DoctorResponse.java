package com.github.beatrizgomees.api.rheumaPlanner.domain.doctor;

import com.couchbase.client.core.deps.com.google.type.PhoneNumber;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;

import java.util.UUID;

public record DoctorResponse (UUID id, String firstName, String lastname, String description, MedicalSpecialty medicalSpecialty, String phoneNumber) {
}
