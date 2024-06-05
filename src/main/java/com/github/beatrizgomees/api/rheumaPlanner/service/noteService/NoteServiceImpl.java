package com.github.beatrizgomees.api.rheumaPlanner.service.noteService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteDTO;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class NoteServiceImpl implements CrudService<NoteDTO, Document> {

    @Inject
    DataManager dataManager;

    public NoteServiceImpl() {}


    @Override
    public NoteDTO create(NoteDTO request) {
        var entity = new NoteDTO(
                UUID.randomUUID(),
                request.title(),
                request.description(),
                request.doctor(),
                request.createdAt(),
                request.dateConsult(),
                request.todoLists()

        );
        Document document = new Document()
                .append("id", entity.id().toString())
                .append("title", entity.title())
                .append("description", entity.description())
                .append("doctor", entity.doctor())
                .append("todoLists", entity.todoLists())
                .append("createdAt", entity.createdAt())
                .append("dateConsult", entity.dateConsult());
        dataManager.create(document, getCollectionName());
        return request;
    }

    @Override
    public List<Document> getAll() {
        return dataManager.getAll(getCollectionName());
    }

    @Override
    public Optional<Document> findById(UUID id) {
        return Optional.ofNullable(dataManager.findByIdGeneral(id, getCollectionName()));
    }

    @Override
    public void delete(UUID id) {
        var idExist = existById(id);
        if (idExist.isEmpty()) {
            throw new RuntimeException("note is not found");
        } else {
            dataManager.delete(id, getCollectionName());
        }
    }

    @Override
    public Optional<Document> update(UUID id, Document updateDocument) {
        Optional<Document> document = findById(id);
        try {
            if (document == null) {
                throw new IllegalStateException("note not found");
            }
            Document update = new Document("$set", updateDocument);
            dataManager.updateGenereal(id, update, "note");

        } catch (IllegalStateException e){
            throw new IllegalArgumentException("Error finding note by ID", e);
        }
        return document;
    }

    @Override
    public  Optional<Boolean> existById(UUID id) {
        Document exist = dataManager.findByIdGeneral(id, getCollectionName());
        if (exist == null || exist.isEmpty()) {
            throw new RuntimeException("note is not found");
        }else{
            return Optional.of(true);
        }
    }

    private String getCollectionName() {
        return "notes";
    }
}
