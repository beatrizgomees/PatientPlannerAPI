package com.github.beatrizgomees.api.rheumanotes.doctor.service;

import com.github.beatrizgomees.api.rheumanotes.core.data.DataManager;
import com.github.beatrizgomees.api.rheumanotes.core.interfaces.CrudService;
import com.github.beatrizgomees.api.rheumanotes.doctor.dto.DoctorRequest;
import com.github.beatrizgomees.api.rheumanotes.core.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumanotes.core.exceptions.GetException;
import com.github.beatrizgomees.api.rheumanotes.medicalSpecialty.entity.MedicalSpecialty;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DoctorServiceImpl implements CrudService<DoctorRequest, Document> {

    @Inject
    DataManager dataManager;


    @Override
    public DoctorRequest create(DoctorRequest request) {
        if (existsMedicalSpecialtyByName(request)) {
            if (!existsDoctorByName(request)) {
                Document document = new Document()
                        .append("name", request.name())
                        .append("description", request.description())
                        .append("lastname", request.lastname())
                        .append("medicalSpecialty", request.medicalSpecialty())
                        .append("PhoneNumber", request.phoneNumber());
                dataManager.create(document, "doctor");
                return request;

            } else {
                throw new IllegalStateException("Médico já  está cadastrada.");
            }
        }
        throw new IllegalStateException("Especalidade Médica não está cadastrada.");
    }

    @Override
    public List<Document> getAll() {
        List<Document> documentList = new ArrayList<>();
        FindIterable<Document> documents = null;

        FindIterable<Document> documentsReturn = dataManager.getAll(documents, "doctor");

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
            Document noteReturn = dataManager.findByIdGeneral(id, "doctor");

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
            dataManager.updateGenereal(id, update, "doctor");
            return document;
        } catch (FindByIdException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindByIdException("Error finding Doctor by ID", e);
        }
    }

    public boolean existsDoctorByName(DoctorRequest request) {
        Document existingSpecialty = dataManager.findByNameGeneral(String.valueOf(request.name()), "doctor");
        if (existingSpecialty == null || existingSpecialty.isEmpty()) {
            throw new IllegalStateException("Nao existe um medico com esse nome.");
        }else{
            return true;
        }
    }

    @Override
    public boolean existsMedicalSpecialtyByName(DoctorRequest request) {
        Document existingSpecialty = dataManager.findByNameGeneral(String.valueOf(request.medicalSpecialty().getName()), "medicalSpecialty");
        if (existingSpecialty == null || existingSpecialty.isEmpty()) {
            throw new IllegalStateException("Nao existe uma especialidade com esse nome.");
        }else{
            return true;
        }
    }
}
