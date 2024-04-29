package com.github.beatrizgomees.api.rheumaPlanner.todoList.mapper;

import com.github.beatrizgomees.api.rheumaPlanner.notes.dto.NoteRequest;
import com.github.beatrizgomees.api.rheumaPlanner.notes.entity.Note;
import com.github.beatrizgomees.api.rheumaPlanner.todoList.dto.TodoListRequest;
import com.github.beatrizgomees.api.rheumaPlanner.todoList.entity.TodoList;

public class TodoListMapper {

    public TodoList toEntity(TodoListRequest todoListRequest){
        TodoList todoList = new TodoList();
        todoList.setTitle(todoListRequest.title());
        todoList.setDescription(todoListRequest.description());
        todoList.setDone(todoListRequest.done());
        todoList.setReminder(todoListRequest.reminder());

        return todoList;
    }
}
