package com.github.beatrizgomees.api.rheumaPlanner.service.todoListService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListRequest;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class TodoListServiceImpl implements CrudService<TodoListDTO, Document> {


    public TodoListServiceImpl() {

    }


    @Override
    public TodoListDTO create(TodoListDTO request) {
        return null;
    }

    @Override
    public List<Document> getAll() {
        return List.of();
    }

    @Override
    public Optional<Document> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Optional<Document> update(UUID id, Document updateDocument) throws FindByIdException {
        return Optional.empty();
    }

    @Override
    public  Optional<Boolean> existById(UUID id) {
        return Optional.of(false);
    }
}
