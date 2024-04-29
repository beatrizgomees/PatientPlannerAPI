package com.github.beatrizgomees.api.rheumaPlanner.todoList.service;

import com.github.beatrizgomees.api.rheumaPlanner.core.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.GetException;
import com.github.beatrizgomees.api.rheumaPlanner.core.interfaces.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.todoList.dto.TodoListRequest;
import com.github.beatrizgomees.api.rheumaPlanner.todoList.entity.TodoList;
import com.github.beatrizgomees.api.rheumaPlanner.todoList.mapper.TodoListMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class TodoListServiceImpl implements CrudService<TodoListRequest, Document> {

    @Inject
    DataManager dataManager;

    @Override
    public TodoListRequest create(TodoListRequest request) {
        TodoListMapper todoListMapper = new TodoListMapper();
        TodoList todoList = todoListMapper.toEntity(request);

        Document document = new Document()
                .append("title", todoList.getTitle())
                .append("description", todoList.getDescription())
                .append("reminder", todoList.getReminder())
                .append("done", todoList.isDone());

        dataManager.create(document, "todo_list");
        return request;
    }

    @Override
    public List<Document> getAll() {
        List<Document> documentList = new ArrayList<>();
        FindIterable<Document> documents = null;

        FindIterable<Document> documentsReturn = dataManager.getAll(documents, "todo_list");

        try (MongoCursor<Document> cursor = documentsReturn.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                documentList.add(document);
            }
        }catch (GetException notesException){
            new GetException("Not possible return todo list");
        }

        return documentList;
    }

    @Override
    public Document findById(String id) throws FindByIdException {
        try {

            Document noteReturn = dataManager.findByIdGeneral(id, "todo_list");

            if (noteReturn == null) {
                throw new FindByIdException("todo list not found");
            }
            return noteReturn;
        } catch (FindByIdException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindByIdException("Error finding todo list by ID", e);
        }
    }

    @Override
    public void delete(String id) {
        dataManager.delete(id, "todo_list");
    }

    @Override
    public Document update(String id, Document updateDocument) throws FindByIdException {
        try {

            Document document = findById(String.valueOf(id));
            if (document == null) {
                throw new FindByIdException("todo_list not found");
            }
            Document update = new Document("$set", updateDocument);
            dataManager.updateGenereal(id, update, "todo_list");
            return document;

        } catch (FindByIdException e) {
            throw new FindByIdException("Error finding todo_list by ID", e);
        }
    }

    @Override
    public boolean existsMedicalSpecialtyByName(TodoListRequest request) {
      return false;
    }
}
