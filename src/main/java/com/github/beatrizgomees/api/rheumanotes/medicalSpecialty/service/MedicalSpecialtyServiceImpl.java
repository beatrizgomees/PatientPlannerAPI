package com.github.beatrizgomees.api.rheumanotes.medicalSpecialty.service;

import com.github.beatrizgomees.api.rheumanotes.core.data.DataManager;
import com.github.beatrizgomees.api.rheumanotes.core.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumanotes.core.exceptions.GetException;
import com.github.beatrizgomees.api.rheumanotes.core.interfaces.CrudService;
import com.github.beatrizgomees.api.rheumanotes.medicalSpecialty.dto.MedicalSpecialtyRequest;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class MedicalSpecialtyServiceImpl implements CrudService<MedicalSpecialtyRequest, Document> {

    @Inject
    DataManager dataManager;
    @Override
    public MedicalSpecialtyRequest create(MedicalSpecialtyRequest request) {
        if(!existsMedicalSpecialtyByName(request)) {
            Document document = new Document()
                    .append("name", request.name())
                    .append("description", request.description());
            dataManager.create(document, "medicalSpecialty");
            return request;
        }else{
            throw new IllegalStateException("Especialidade Medica já está cadastrada");
        }
    }

    @Override
    public List<Document> getAll() {
        List<Document> documentList = new ArrayList<>();
        FindIterable<Document> documents = null;

        FindIterable<Document> documentsReturn = dataManager.getAll(documents, "medicalSpecialty");

        try (MongoCursor<Document> cursor = documentsReturn.iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                documentList.add(document);
            }
        }catch (GetException notesException){
            new GetException("Not possible return doctors");
        }
        return documentList;
    }

    @Override
    public Document findById(String id) throws FindByIdException {
        try {
            Document noteReturn = dataManager.findByIdGeneral(id, "medicalSpecialty");

            if (noteReturn == null) {
                throw new FindByIdException("Doctor not found");
            }
            return noteReturn;
        } catch (FindByIdException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindByIdException("Error finding doctor by ID", e);
        }
    }

    @Override
    public void delete(String id) {
        dataManager.delete(id);

    }

    @Override
    public Document update(String id, Document updateDocument) throws FindByIdException {
        try {
            Document document = findById(String.valueOf(id));
            if (document == null) {
                throw new FindByIdException("Doctor not found");
            }
            Document update = new Document("$set", updateDocument);
            dataManager.updateGenereal(id, update, "medicalSpecialty");
            return document;
        } catch (FindByIdException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindByIdException("Error finding Doctor by ID", e);
        }
    }


    @Override
    public boolean existsMedicalSpecialtyByName(MedicalSpecialtyRequest request) {
        Document existingSpecialty = dataManager.findByNameGeneral(String.valueOf(request.name()), "medicalSpecialty");
        if (existingSpecialty == null || existingSpecialty.isEmpty()) {
            throw new IllegalStateException("Nao existe uma especialidade com esse nome.");
        }else{
            return true;
        }
    }
}
