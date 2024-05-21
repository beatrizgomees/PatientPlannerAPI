package com.github.beatrizgomees.api.rheumaPlanner.domain.note;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.Note;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteRequest;
import org.bson.Document;


public class NoteMapper {

    public Note toEntity(NoteRequest noteRequest){
        Note note = new Note();
        note.setTitle(noteRequest.title());
        note.setDescription(noteRequest.description());
        note.setDoctor(noteRequest.doctor());
        note.setCreatedAt(noteRequest.createdAt());
        note.setDateConsult(noteRequest.dateConsult());
        return note;
    }


    public Document toDocument(NoteRequest noteRequest) {
        return new Document()
                .append("title", noteRequest.title())
                .append("description", noteRequest.description())
                .append("doctor", noteRequest.doctor())
                .append("createdAt", noteRequest.createdAt())
                .append("dateConsult", noteRequest.dateConsult());
    }

}
