package com.github.beatrizgomees.api.rheumaPlanner.service.noteService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteRequest;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class NoteServiceImpl implements CrudService<NoteDTO, Document> {


    public NoteServiceImpl() {

    }

    public NoteDTO convertRequestToDTO(NoteRequest request) {
        NoteMapper mapper = new NoteMapper();
        return mapper.convertRequestToDTO(request);
    }

    @Override
    public NoteDTO create(NoteDTO request) {
        return null;
    }

    @Override
    public List<Document> getAll() {
        return List.of();
    }

    @Override
    public Optional<Document> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Optional<Document> update(UUID id, Document updateDocument) throws FindByIdException {
        return Optional.empty();
    }

    @Override
    public  Optional<Boolean> existById(UUID id) {
        return Optional.of(false);
    }
}
