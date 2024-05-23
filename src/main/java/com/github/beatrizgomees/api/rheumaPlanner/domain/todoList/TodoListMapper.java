package com.github.beatrizgomees.api.rheumaPlanner.domain.todoList;

import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteResponse;
import org.bson.Document;

public class TodoListMapper {

    public TodoList convertDtoToEntity(TodoListDTO todoListDTO){
        TodoList todoList = new TodoList();
        todoList.setTitle(todoListDTO.title());
        todoList.setDescription(todoListDTO.description());
        todoList.setReminder(todoListDTO.reminder());
        todoList.setDone(todoListDTO.done());
        return todoList;
    }


    public Document convertDtoToDocument(TodoListDTO todoListDTO) {
        if (todoListDTO == null) {
            return null;
        }

        return new Document()
                .append("title", todoListDTO.title())
                .append("description", todoListDTO.description())
                .append("reminder", todoListDTO.reminder())
                .append("done", todoListDTO.done());


    }

    public TodoListResponse convertEntityToResponse(TodoList todoList) {
        if (todoList == null) {
            return null;
        }
        return new TodoListResponse(
                todoList.getId(),
                todoList.getTitle(),
                todoList.getDescription(),
                todoList.getReminder(),
                todoList.isDone()

        );

    }
    public TodoListDTO convertRequestToDTO(TodoListRequest todoListRequest) {
        if (todoListRequest == null) {
            return null;
        }
        return new TodoListDTO(
                todoListRequest.id(),
                todoListRequest.title(),
                todoListRequest.description(),
                todoListRequest.reminder(),
                todoListRequest.done()
            );


    }
}
