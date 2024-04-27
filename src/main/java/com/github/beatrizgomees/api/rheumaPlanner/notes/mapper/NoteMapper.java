package com.github.beatrizgomees.api.rheumaPlanner.notes.mapper;

import com.github.beatrizgomees.api.rheumaPlanner.notes.entity.Note;
import com.github.beatrizgomees.api.rheumaPlanner.notes.dto.NoteRequest;


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
