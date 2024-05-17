package com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.service;

import com.github.beatrizgomees.api.rheumaPlanner.core.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.GetException;
import com.github.beatrizgomees.api.rheumaPlanner.core.interfaces.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.dto.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.entity.MedicalSpecialty;
import com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.mapper.MedicalSpecialtyMapper;
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
        if(existsMedicalSpecialtyByName(request)) {
            MedicalSpecialtyMapper medicalSpecialtyMapper = new MedicalSpecialtyMapper();
            MedicalSpecialty medicalSpecialty = medicalSpecialtyMapper.toEntity(request);
            Document document = new Document()
                    .append("name", medicalSpecialty.getName())
                    .append("description", medicalSpecialty.getDescription());
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
            throw new FindByIdException("Error finding note by ID", e);
        }
    }


    @Override
    public boolean existsMedicalSpecialtyByName(MedicalSpecialtyRequest request) {
        Document existingSpecialty = dataManager.findByNameGeneral(String.valueOf(request.name()), "medicalSpecialty");
        if (existingSpecialty == null || existingSpecialty.isEmpty()) {
            return true;
        }else{
            throw new IllegalStateException("Nao existe uma especialidade com esse nome.");
        }
    }
}