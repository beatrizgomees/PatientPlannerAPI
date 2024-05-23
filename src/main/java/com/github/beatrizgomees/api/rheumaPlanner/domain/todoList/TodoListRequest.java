package com.github.beatrizgomees.api.rheumaPlanner.domain.todoList;

import java.time.LocalDateTime;
import java.util.UUID;

public record TodoListRequest (UUID id, String title, String description, boolean done, LocalDateTime reminder){
}
