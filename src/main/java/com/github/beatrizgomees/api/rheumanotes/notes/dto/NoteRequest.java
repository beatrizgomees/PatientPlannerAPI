package com.github.beatrizgomees.api.rheumanotes.notes.dto;

import com.github.beatrizgomees.api.rheumanotes.doctor.entity.Doctor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record NoteRequest (UUID id, String title, String description, Doctor doctor, LocalDateTime dateConsult, LocalDateTime createdAt){
}
