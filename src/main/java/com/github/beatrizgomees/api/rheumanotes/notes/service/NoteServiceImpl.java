package com.github.beatrizgomees.api.rheumanotes.notes.service;

import com.github.beatrizgomees.api.rheumanotes.core.interfaces.CrudService;
import com.github.beatrizgomees.api.rheumanotes.core.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumanotes.core.exceptions.GetException;
import com.github.beatrizgomees.api.rheumanotes.notes.dto.NoteRequest;
import com.github.beatrizgomees.api.rheumanotes.core.data.DataManager;
import com.github.beatrizgomees.api.rheumanotes.notes.entity.Note;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NoteServiceImpl implements CrudService<NoteRequest, Document> {

    @Inject
    DataManager dataManager;


    @Override
    public NoteRequest create(NoteRequest request) {
    if(existsDoctorByName(request) && existsMedicalSpecialtyByName(request)) {

        Document document = new Document()
                .append("title", request.title())
                .append("description", request.description())
                .append("doctor", request.doctor())
                .append("dateConsult", request.dateConsult())
                .append("createdAt", request.createdAt());
        dataManager.create(document, "notes");
        return request;
    }else{
        throw new IllegalStateException("Especialidade Medica ou Medico não está cadastrada. Por favor, cadastre antes");
    }

    }

    @Override
    public List<Document> getAll() throws GetException {
        List<Document> documentList = new ArrayList<>();
        FindIterable<Document> documents = null;

        FindIterable<Document> documentsReturn = dataManager.getAll(documents, "notes");

        try (MongoCursor<Document> cursor = documentsReturn.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                documentList.add(document);
            }
        }catch (GetException notesException){
            new GetException("Not possible return notes");
        }

        return documentList;
    }

    @Override
    public Document findById(String id) throws FindByIdException {
        try {

            Document noteReturn = dataManager.findByIdGeneral(id, "notes");

            if (noteReturn == null) {
                throw new FindByIdException("Note not found");
            }
            return noteReturn;
        } catch (FindByIdException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindByIdException("Error finding note by ID", e);
        }
    }

    @Override
    public void delete(String id){
        dataManager.delete(id, "notes");
    }

    @Override
    public Document update(String id, Document updateDocument) throws FindByIdException {
        try {

            Document document = findById(String.valueOf(id));
            if (document == null) {
                throw new FindByIdException("Note not found");
            }
            Document update = new Document("$set", updateDocument);
            dataManager.updateGenereal(id, update, "notes");
            return document;
        } catch (FindByIdException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindByIdException("Error finding note by ID", e);
        }
    }

    public boolean existsDoctorByName(NoteRequest request){
        Document existingSpecialty = dataManager.findByNameGeneral(String.valueOf(request.doctor().getName()), "doctor");
        if (existingSpecialty == null || existingSpecialty.isEmpty()) {
            throw new IllegalStateException("Nao existe um medico com esse nome.");
        }else{
            return true;
        }
    }

    @Override
    public boolean existsMedicalSpecialtyByName(NoteRequest request){
        Document existingSpecialty = dataManager.findByNameGeneral(String.valueOf(request.doctor().getMedicalSpecialty().getName()), "medicalSpecialty");
        if (existingSpecialty == null || existingSpecialty.isEmpty()) {
            throw new IllegalStateException("Nao existe uma especialidade com esse nome.");
        }else{
            return true;
        }
    }


}
