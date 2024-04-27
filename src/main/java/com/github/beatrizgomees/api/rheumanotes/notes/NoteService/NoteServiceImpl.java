package com.github.beatrizgomees.api.rheumanotes.notes.NoteService;

import com.github.beatrizgomees.api.rheumanotes.notes.exceptions.FindNoteByIdException;
import com.github.beatrizgomees.api.rheumanotes.notes.exceptions.GetNotesException;
import com.github.beatrizgomees.api.rheumanotes.notes.dto.NoteRequest;
import com.github.beatrizgomees.api.rheumanotes.notes.data.NoteData;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NoteServiceImpl implements NoteService{

    @Inject
    NoteData noteData;

    @Override
    public NoteRequest createNote(NoteRequest noteRequest) {
        Document document = new Document()
                .append("title", noteRequest.title())
                .append("description", noteRequest.description())
                .append("doctor", noteRequest.doctor())
                .append("dateConsult", noteRequest.dateConsult())
                .append("createdAt", noteRequest.createdAt());
       noteData.createNote(document);
        return noteRequest;
    }

    @Override
    public List<Document> getNotes() throws GetNotesException {
        List<Document> documentList = new ArrayList<>();
        FindIterable<Document> documents = null;

        FindIterable<Document> documentsReturn = noteData.getAllNote(documents);

        try (MongoCursor<Document> cursor = documentsReturn.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                documentList.add(document);
            }
        }catch (GetNotesException notesException){
            new GetNotesException("Not possible return notes");
        }

        return documentList;
    }

    @Override
    public Document findNoteById(String id) throws FindNoteByIdException {
        try {
            Document noteReturn = noteData.findNoteById(id);

            if (noteReturn == null) {
                throw new FindNoteByIdException("Note not found");
            }
            return noteReturn;
        } catch (FindNoteByIdException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindNoteByIdException("Error finding note by ID", e);
        }
    }

    @Override
    public void deleteNote(String id){
        noteData.deleteNoteById(id);
    }



}
