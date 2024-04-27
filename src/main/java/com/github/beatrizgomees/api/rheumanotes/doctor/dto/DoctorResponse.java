package com.github.beatrizgomees.api.rheumanotes.doctor.dto;

import com.couchbase.client.core.deps.com.google.type.PhoneNumber;

import java.util.UUID;

public record DoctorResponse (UUID id, String name, String lastname, String description, String medicalSpecialty, PhoneNumber phoneNumber) {
}
