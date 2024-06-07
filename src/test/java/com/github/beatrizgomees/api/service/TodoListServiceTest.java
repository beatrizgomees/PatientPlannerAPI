package com.github.beatrizgomees.api.service;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoList;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListDTO;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.service.todoListService.TodoListServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TodoListServiceTest {


    @Mock
    private DataManager dataManager;

    @Mock
    private TodoListServiceImpl todoListService;

    @Captor
    private ArgumentCaptor<TodoListDTO> todoListArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> UUIDArgumentCaptor;




    @Nested
    class createTodoList{
        @Test
        @DisplayName("should create todo list with sucess")
        void shouldCreateTodoListWithSucess() {

            var todoList = new TodoListDTO(
                    UUID.randomUUID(),
                    "teste",
                    "teste",
                    LocalDateTime.now(),
                    false
            );

            doReturn(todoList).when(todoListService).create(todoListArgumentCaptor.capture());
            var output = todoListService.create(todoList);

            assertNotNull(output);

            var todoListcaptor = todoListArgumentCaptor.getValue();
            assertEquals(todoList.id(), todoListcaptor.id());


        }
    }

}
