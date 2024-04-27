package com.github.beatrizgomees.api.rheumaPlanner.notes.dto;

import java.time.LocalDateTime;

public record NoteResponse (String title, String description, String doctor, LocalDateTime dateConsult, LocalDateTime createdAt){
}
