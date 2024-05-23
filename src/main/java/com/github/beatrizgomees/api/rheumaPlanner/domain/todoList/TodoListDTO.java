package com.github.beatrizgomees.api.rheumaPlanner.domain.todoList;

import java.time.LocalDateTime;
import java.util.UUID;

public record TodoListDTO(
        UUID id,
        String title,
        String description,
        LocalDateTime reminder,
        boolean done) {
}

