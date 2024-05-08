package com.github.beatrizgomees.api.rheumaPlanner.domain.note;

import com.github.beatrizgomees.api.rheumaPlanner.domain.note.Note;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteRequest;


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



}
