package com.github.beatrizgomees.api.rheumaPlanner.domain.note;

import java.time.LocalDateTime;

public record NoteResponse (String title, String description, String doctor, LocalDateTime dateConsult, LocalDateTime createdAt){
}
