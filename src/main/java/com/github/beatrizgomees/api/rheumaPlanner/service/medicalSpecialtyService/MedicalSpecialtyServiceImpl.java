package com.github.beatrizgomees.api.rheumaPlanner.service.medicalSpecialtyService;


import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.bson.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class MedicalSpecialtyServiceImpl implements CrudService<MedicalSpecialtyDTO, Document> {

    @Inject
    DataManager dataManager;


    private String getCollectionName() {
        return "specialty";
    }


    public MedicalSpecialtyDTO convertRequestToDTO(MedicalSpecialtyRequest request) {
        MedicalSpecialtyMapper mapper = new MedicalSpecialtyMapper();
        return mapper.convertRequestToDTO(request);
    }




    @Override
    public MedicalSpecialtyDTO create(MedicalSpecialtyDTO medicalSpecialtyDTO) {
        if (!existsMedicalSpecialtyByName(medicalSpecialtyDTO)) {

                var entity = new MedicalSpecialtyDTO(
                        UUID.randomUUID(),
                        medicalSpecialtyDTO.name(),
                        medicalSpecialtyDTO.description()

                );
                Document document = new Document()
                        .append("id", entity.id())
                        .append("name", entity.name())
                        .append("description", entity.description());

                dataManager.create(document, getCollectionName());
                return medicalSpecialtyDTO;

            } else {
                throw new IllegalStateException("Especialidade já  está cadastrada.");
            }
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
        try {
            var idExist = existById(id);
            if(idExist.isPresent()) {
                dataManager.delete(id, getCollectionName());
            }
        }catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Document> update(UUID id, Document updateDocument) {
        Optional<Document> document = findById(id);
        try {
            if (document == null) {
                throw new FindByIdException("Medical specialty not found");
            }
            Document update = new Document("$set", updateDocument);
            dataManager.updateGenereal(id, update, getCollectionName());

        } catch (FindByIdException e) {
            throw new IllegalStateException("Error finding medical specialty by ID", e);
        }
        return document;
    }

    @Override
    public Optional<Boolean> existById(UUID id) {
        Document exist = dataManager.findByIdGeneral(id, getCollectionName());
        if (exist == null || exist.isEmpty()) {
            throw new IllegalStateException();
        }else{
            return Optional.of(true);
        }
    }


    public boolean existsMedicalSpecialtyByName(MedicalSpecialtyDTO medicalSpecialtyDTO) {
        Document existingSpecialty = dataManager.findEspecialtyByName(String.valueOf(medicalSpecialtyDTO.name()), getCollectionName());
        if (existingSpecialty == null || existingSpecialty.isEmpty()) {
          return false;
        } else {
            return true;
        }
    }
}







