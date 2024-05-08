package com.github.beatrizgomees.api.rheumaPlanner.service.noteService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.GetException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.Medicine;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteRequest;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.Note;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteMapper;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NoteServiceImpl extends BaseCrudService<NoteRequest, Document> {


    public NoteServiceImpl(DataManager dataManager) {
        super(dataManager);
    }

    public NoteServiceImpl(){

    }

    String[] fieldNames = getFieldNames(Note.class);


    @Override
    public NoteRequest create(NoteRequest request) {
        NoteMapper noteMapper = new NoteMapper();
        Note note = noteMapper.toEntity(request);
        return request;

    }

    @Override
    public String getCollectionName() {
        return "notes";
    }
}
