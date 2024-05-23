package com.github.beatrizgomees.api.rheumaPlanner.service.todoListService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;


@ApplicationScoped
public class TodoListServiceImpl extends BaseCrudService<TodoListDTO, TodoListRequest> {


    public TodoListServiceImpl() {

    }

    @Override
    public String getCollectionName() {
        return "todoList";
    }


    @Override
    public TodoListDTO convertRequestToDTO(TodoListRequest request) {
        TodoListMapper mapper = new TodoListMapper();
        return mapper.convertRequestToDTO(request);
    }
}
