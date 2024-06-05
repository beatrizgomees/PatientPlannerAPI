package com.github.beatrizgomees.api.rheumaPlanner.service.todoListService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListRequest;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class TodoListServiceImpl implements CrudService<TodoListDTO, Document> {

    @Inject
    DataManager dataManager;

    public TodoListServiceImpl() {

    }


    @Override
    public TodoListDTO create(TodoListDTO request) {
        var entity = new TodoListDTO(
                UUID.randomUUID(),
                request.title(),
                request.description(),
                request.reminder(),
                request.done()

        );
        Document document = new Document()
                .append("id", entity.id().toString())
                .append("title", entity.title())
                .append("description", entity.description())
                .append("done", entity.done())
                .append("reminder", entity.reminder());

        dataManager.create(document, getCollectionName());
        return request;
    }

    @Override
    public List<Document> getAll() {
        return dataManager.getAll(getCollectionName());
    }

    @Override
    public Optional<Document> findById(UUID id) {
        return Optional.ofNullable(dataManager.findByIdGeneral(id, getCollectionName()));
    }

    @Override
    public void delete(UUID id) {
        var idExist = existById(id);
        if (idExist.isEmpty()) {
            throw new RuntimeException("todolist is not found");
        } else {
            dataManager.delete(id, getCollectionName());
        }
    }

    @Override
    public Optional<Document> update(UUID id, Document updateDocument) {
        Optional<Document> document = findById(id);
        try {
            if (document == null) {
                throw new IllegalStateException("todolist not found");
            }
            Document update = new Document("$set", updateDocument);
            dataManager.updateGenereal(id, update, "todolist");

        } catch (IllegalStateException e){
            throw new IllegalArgumentException("Error finding todolist by ID", e);
        }
        return document;
    }

    @Override
    public  Optional<Boolean> existById(UUID id) {
        Document exist = dataManager.findByIdGeneral(id, getCollectionName());
        if (exist == null || exist.isEmpty()) {
            throw new RuntimeException("todolist is not found");
        }else{
            return Optional.of(true);
        }
    }

    private String getCollectionName() {
        return "todolist";
    }
}
