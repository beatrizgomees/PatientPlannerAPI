package com.github.beatrizgomees.api.rheumaPlanner.domain.todoList;

import java.time.LocalDateTime;

public record TodoListRequest (String title, String description, boolean done, LocalDateTime reminder){
}
