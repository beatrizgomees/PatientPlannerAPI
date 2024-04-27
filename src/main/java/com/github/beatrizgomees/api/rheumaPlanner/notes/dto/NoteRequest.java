package com.github.beatrizgomees.api.rheumaPlanner.notes.dto;

import com.github.beatrizgomees.api.rheumaPlanner.doctor.entity.Doctor;

import java.time.LocalDateTime;
import java.util.UUID;

public record NoteRequest (UUID id, String title, String description, Doctor doctor, LocalDateTime dateConsult, LocalDateTime createdAt){
}
