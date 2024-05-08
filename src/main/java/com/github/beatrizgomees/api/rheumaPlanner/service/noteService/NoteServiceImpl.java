package com.github.beatrizgomees.api.rheumaPlanner.service.noteService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteRequest;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.Note;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

@ApplicationScoped
public class NoteServiceImpl extends BaseCrudService<NoteRequest, Document> {



    public NoteServiceImpl(){

    }



    @Override
    public String getCollectionName() {
        return "notes";
    }


}
