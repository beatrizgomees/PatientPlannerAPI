package com.github.beatrizgomees.api.rheumaPlanner.service.noteService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

@ApplicationScoped
public class NoteServiceImpl extends BaseCrudService<NoteDTO, NoteRequest> {


    public NoteServiceImpl() {

    }


    @Override
    public String getCollectionName() {
        return "notes";
    }

    @Override
    public NoteDTO convertRequestToDTO(NoteRequest request) {
        NoteMapper mapper = new NoteMapper();
        return mapper.convertRequestToDTO(request);
    }
}
