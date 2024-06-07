package com.github.beatrizgomees.api.rheumaPlanner.domain.note;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record NoteRequest (
        UUID id,
        String title,
        String description,
        DoctorDTO doctor,
        LocalDateTime dateConsult,
        LocalDateTime createdAt,
        List<TodoListDTO> todoList){
}
