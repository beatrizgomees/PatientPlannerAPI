package com.github.beatrizgomees.api.rheumanotes.notes.NoteService;

import com.github.beatrizgomees.api.rheumanotes.notes.dto.NoteRequest;
import com.github.beatrizgomees.api.rheumanotes.notes.entity.Note;
import com.github.beatrizgomees.api.rheumanotes.notes.exceptions.FindNoteByIdException;
import org.bson.Document;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    NoteRequest createNote(NoteRequest noteRequest);
    List<Document> getNotes();

    Document findNoteById(String id) throws FindNoteByIdException;

    void deleteNote(String id);

}
