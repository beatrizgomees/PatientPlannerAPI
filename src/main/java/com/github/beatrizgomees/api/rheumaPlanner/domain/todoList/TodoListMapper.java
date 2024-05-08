package com.github.beatrizgomees.api.rheumaPlanner.domain.todoList;

import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoList;

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
