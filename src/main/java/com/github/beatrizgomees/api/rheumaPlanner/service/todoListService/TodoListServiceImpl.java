package com.github.beatrizgomees.api.rheumaPlanner.service.todoListService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyMapper;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.GetException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoList;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListMapper;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class TodoListServiceImpl extends BaseCrudService<TodoListRequest, Document> {



    public TodoListServiceImpl(){

    }


    @Override
    public String getCollectionName() {
        return "todoList";
    }


}
