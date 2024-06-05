package com.github.beatrizgomees.api.rheumaPlanner.service.medicineService;


import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListDTO;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class MedicineServiceImpl implements CrudService<MedicineDTO, Document> {

    @Inject
    DataManager dataManager;


    public MedicineServiceImpl() {

    }


    @Override
    public MedicineDTO create(MedicineDTO request) {
        try {
            var exist = existById(request.id());
            if(!exist.isPresent()){
            var entity = new MedicineDTO(
                    UUID.randomUUID(),
                    request.name(),
                    request.description(),
                    request.amount(),
                    request.reminder(),
                    request.medicalSpecialty()
            );
            Document document = new Document()
                    .append("id", entity.id().toString())
                    .append("name", entity.name())
                    .append("description", entity.description())
                    .append("amount", entity.amount())
                    .append("medicalSpecialty", entity.medicalSpecialty())
                    .append("reminder", entity.reminder());
            dataManager.create(document, getCollectionName());

            return request;
            }else{
                throw new IllegalStateException("Medicine already exists");
            }
        }catch (IllegalArgumentException e) {
            throw new IllegalStateException(("Medicine alredy register"));
        }
    }



    @Override
    public List<Document> getAll() {
        return dataManager.getAll(getCollectionName());
    }

    @Override
    public Optional<Document> findById(UUID id) {
        try {
         return Optional.ofNullable(dataManager.findByIdGeneral(id, getCollectionName()));
        }catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(UUID id) {
        var medicineExist = existById(id);
        if(!medicineExist.isPresent()){
            throw new IllegalStateException(("Medicine not found"));
        }else{
            dataManager.delete(id, getCollectionName());
        }

    }

    @Override
    public Optional<Document> update(UUID id, Document updateDocument) {
        Optional<Document> document = findById(id);
        try {
            if (document == null) {
                throw new FindByIdException("Medicine not found");
            }
            Document update = new Document("$set", updateDocument);
            dataManager.updateGenereal(id, update, "medicine");

        } catch (FindByIdException e) {
            throw new IllegalArgumentException("Error finding Medicine by ID", e);
        }
        return document;
    }

    @Override
    public  Optional<Boolean> existById(UUID id) {
        Document exist = dataManager.findByIdGeneral(id, getCollectionName());
        if (exist == null || exist.isEmpty()) {
            throw new RuntimeException("Medicine is not found");
        }else{
            return Optional.of(true);
        }
    }

    private String getCollectionName() {
        return "medicine";
    }

}
