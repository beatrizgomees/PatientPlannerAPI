package com.github.beatrizgomees.api.rheumaPlanner.medicines.service;

import com.github.beatrizgomees.api.rheumaPlanner.core.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.GetException;
import com.github.beatrizgomees.api.rheumaPlanner.core.interfaces.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.entity.MedicalSpecialty;
import com.github.beatrizgomees.api.rheumaPlanner.medicines.dto.MedicineRequest;
import com.github.beatrizgomees.api.rheumaPlanner.medicines.entity.Medicine;
import com.github.beatrizgomees.api.rheumaPlanner.medicines.mapper.MedicineMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class MedicineServiceImpl implements CrudService<MedicineRequest, Document> {

    @Inject
    DataManager dataManager;

    public MedicineServiceImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public MedicineRequest create(MedicineRequest request) {

        MedicineMapper medicineMapper = new MedicineMapper();
        Medicine medicine = medicineMapper.toEntity(request);
        Document document = new Document()
                .append("name", medicine.getName())
                .append("description", medicine.getDescription())
                .append("amount", medicine.getAmount())
                .append("reminder", medicine.getReminder());

        dataManager.create(document, "medicines");
        return request;
    }

    @Override
    public List<Document> getAll() {
        List<Document> documentList = new ArrayList<>();
        FindIterable<Document> documents = null;

        FindIterable<Document> documentsReturn = dataManager.getAll(documents, "medicines");

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

            Document noteReturn = dataManager.findByIdGeneral(id, "medicines");

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
    public void delete(String id) {
        dataManager.delete(id, "medicines");

    }

    @Override
    public Document update(String id, Document updateDocument) throws FindByIdException {
        try {
            Document document = findById(String.valueOf(id));
            if (document == null) {
                throw new FindByIdException("Doctor not found");
            }
            Document update = new Document("$set", updateDocument);
            dataManager.updateGenereal(id, update, "medicines");
            return document;
        } catch (FindByIdException e) {
            throw new FindByIdException("Error finding note by ID", e);
        }
    }

    @Override
    public boolean existsMedicalSpecialtyByName(MedicineRequest request) {
        Document existingSpecialty = dataManager.findByNameGeneral(String.valueOf(request.medicalSpecialty().getName()), "medicalSpecialty");
        if (existingSpecialty == null || existingSpecialty.isEmpty()) {
            throw new IllegalStateException("Nao existe uma especialidade com esse nome.");
        }else{
            return true;
        }
    }


}
