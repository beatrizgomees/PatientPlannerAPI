package com.github.beatrizgomees.api.rheumanotes.notes.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record NoteRequest (String title, String description, String doctor, LocalDateTime dateConsult, LocalDateTime createdAt){
}
