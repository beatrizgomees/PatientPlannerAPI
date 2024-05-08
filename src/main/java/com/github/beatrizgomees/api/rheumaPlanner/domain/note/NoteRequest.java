package com.github.beatrizgomees.api.rheumaPlanner.domain.note;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.Doctor;

import java.time.LocalDateTime;
import java.util.UUID;

public record NoteRequest (UUID id, String title, String description, Doctor doctor, LocalDateTime dateConsult, LocalDateTime createdAt){
}
