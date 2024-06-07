package com.github.beatrizgomees.api.service;


import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoList;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListDTO;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.service.noteService.NoteServiceImpl;
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
import java.util.Optional;
import java.util.UUID;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private DataManager dataManager;

    @Mock
    private NoteServiceImpl noteService;


    @Captor
    private ArgumentCaptor<NoteDTO> noteArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> UUIDArgumentCaptor;
    
    
    @Nested
    class createNote{

        @Test
        @DisplayName("should create note with sucess")
        void shouldCreateNoteWithSucess() {

            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );
            var doctor = new DoctorDTO(
                    UUID.randomUUID(),
                    "BEA",
                    "gomes",
                    medicalSpecialty,
                    "819964523",
                    "Meu reumatologista do HC"
            );
            List<TodoListDTO> todoLists = List.of();
            var note = new NoteDTO(
                    UUID.randomUUID(),
                    "Consulta de reumato",
                    "hospital das clinicas",
                    doctor,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    todoLists
            );
            doReturn(note).when(noteService).create(noteArgumentCaptor.capture());


            var output  = noteService.create(note);
            assertNotNull(output);
            var noteCaptor = noteArgumentCaptor.getValue();

            assertEquals(note.id(), noteCaptor.id());

        }
    }

    @Nested
    class getAllNotes{


        @Test
        @DisplayName("should return all todo list ")
        void shouldGetAllTodoList() {

            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );
            var doctor = new DoctorDTO(
                    UUID.randomUUID(),
                    "BEA",
                    "gomes",
                    medicalSpecialty,
                    "819964523",
                    "Meu reumatologista do HC"
            );
            List<TodoListDTO> todoLists = List.of();
            var note = new NoteDTO(
                    UUID.randomUUID(),
                    "Consulta de reumato",
                    "hospital das clinicas",
                    doctor,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    todoLists
            );

            var noteList = List.of(note);
            doReturn(noteList).when(noteService).getAll();

            var output = noteService.getAll();
            assertNotNull(output);

            assertEquals(noteList.size(), output.size());

        }
    }


    @Nested
    class getNoteById{


        @Test
        @DisplayName("Should get note by id with sucess when optional is presente")
        void shouldGetNoteById() {

            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );
            var doctor = new DoctorDTO(
                    UUID.randomUUID(),
                    "BEA",
                    "gomes",
                    medicalSpecialty,
                    "819964523",
                    "Meu reumatologista do HC"
            );
            List<TodoListDTO> todoLists = List.of();
            var note = new NoteDTO(
                    UUID.randomUUID(),
                    "Consulta de reumato",
                    "hospital das clinicas",
                    doctor,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    todoLists
            );

            doReturn(Optional.of(note)).when(noteService).findById(UUIDArgumentCaptor.capture());
            var output = noteService.findById(note.id());

            assertTrue(output.isPresent());
            assertEquals(note.id(),UUIDArgumentCaptor.getValue());

        }

        @Test
        @DisplayName("Should not get note by id when optional is empty")
        void shouldNotGetNoteByIdWhenOptionalIsEmpty() {

            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );
            var doctor = new DoctorDTO(
                    UUID.randomUUID(),
                    "BEA",
                    "gomes",
                    medicalSpecialty,
                    "819964523",
                    "Meu reumatologista do HC"
            );
            List<TodoListDTO> todoLists = List.of();
            var note = new NoteDTO(
                    UUID.randomUUID(),
                    "Consulta de reumato",
                    "hospital das clinicas",
                    doctor,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    todoLists
            );


            doReturn(Optional.empty()).when(noteService).findById(UUIDArgumentCaptor.capture());
            var output = noteService.findById(note.id());

            assertFalse(output.isPresent());
            assertEquals(note.id(),UUIDArgumentCaptor.getValue());
        }

    }


    @Nested
    class deleteNoteById{


        @Test
        @DisplayName("should delete note with sucess")
        void shouldDeleteNoteById() {

            doNothing().when(noteService).delete(UUIDArgumentCaptor.capture());

            var doctorId = UUID.randomUUID();

            noteService.delete(doctorId);

            var idList = UUIDArgumentCaptor.getAllValues();
            assertEquals(doctorId, idList.get(0));
        }
    }


}
