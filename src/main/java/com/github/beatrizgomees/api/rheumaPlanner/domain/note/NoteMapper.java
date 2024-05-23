package com.github.beatrizgomees.api.rheumaPlanner.domain.note;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.Medicine;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineResponse;
import org.bson.Document;


public class NoteMapper {

    public Note convertDtoToEntity(NoteDTO noteDTO){
        Note note = new Note();
        note.setTitle(noteDTO.title());
        note.setDescription(noteDTO.description());
        note.setCreatedAt(noteDTO.createdAt());
        note.setDateConsult(noteDTO.dateConsult());
        note.setDoctor(noteDTO.doctor());
        note.setTodoList(noteDTO.todoLists());
        return note;
    }


    public Document convertDtoToDocument(NoteDTO noteDTO) {
        if (noteDTO == null) {
            return null;
        }

        return new Document()
                .append("title", noteDTO.title())
                .append("doctor", noteDTO.doctor())
                .append("createdAt", noteDTO.createdAt())
                .append("dateConsult", noteDTO.dateConsult())
                .append("todoLists", noteDTO.todoLists())
                .append("description", noteDTO.description());

    }

    public NoteResponse convertEntityToResponse(Note note) {
        if (note == null) {
            return null;
        }
        NoteResponse noteResponse = new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.getDoctor(),
                note.getDateConsult(),
                note.getCreatedAt(),
                note.getTodoList()
        );
        return noteResponse;

    }
    public NoteDTO convertRequestToDTO(NoteRequest noteRequest) {
        if (noteRequest == null) {
            return null;
        }
        return new NoteDTO(
                noteRequest.id(),
                noteRequest.title(),
                noteRequest.description(),
                noteRequest.doctor(),
                noteRequest.dateConsult(),
                noteRequest.createdAt(),
                noteRequest.todoList()


        );
    }
}
