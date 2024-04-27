package com.github.beatrizgomees.api.rheumanotes.notes.mapper;

import com.github.beatrizgomees.api.rheumanotes.notes.entity.Note;
import com.github.beatrizgomees.api.rheumanotes.notes.dto.NoteRequest;
import org.apache.camel.spi.annotations.Component;


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
