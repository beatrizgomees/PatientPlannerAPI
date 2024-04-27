package com.github.beatrizgomees.api.rheumanotes.notes.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record NoteResponse (String title, String description, String doctor, LocalDateTime dateConsult, LocalDateTime createdAt){
}
