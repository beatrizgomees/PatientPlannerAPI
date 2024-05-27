package com.github.beatrizgomees.api.rheumaPlanner.service.doctorService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.*;
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
public class DoctorServiceImpl implements CrudService<DoctorDTO, Document> {

    @Inject
    DataManager dataManager;

    public DoctorServiceImpl() {
    }

    @Override
    public DoctorDTO create(DoctorDTO doctorDTO){
        if (existsMedicalSpecialtyByName(doctorDTO)) {
            if (!existsDoctorByName(doctorDTO)) {
                var entity = new DoctorDTO(
                        UUID.randomUUID(),
                        doctorDTO.firstName(),
                        doctorDTO.lastName(),
                        doctorDTO.medicalSpecialty(),
                        doctorDTO.description(),
                        doctorDTO.phoneNumber()
                     );
                Document document = new Document()
                        .append("id", entity.id().toString())
                        .append("name", entity.firstName())
                        .append("description", entity.description())
                        .append("lastname", entity.lastName())
                        .append("medicalSpecialty", entity.medicalSpecialty())
                        .append("PhoneNumber", entity.phoneNumber());
                dataManager.create(document, getCollectionName());
                return doctorDTO;

            } else {
                throw new IllegalStateException("Médico já  está cadastrada.");
            }
        }
        throw new IllegalStateException("Especalidade Médica não está cadastrada.");    }

    @Override
    public List<Document> getAll() {
        return dataManager.getAll(getCollectionName());


    }

    private String getCollectionName() {
        return "doctor";
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
    public Optional<Document> update(UUID id, Document updateDocument) throws FindByIdException {
        Optional<Document> document = findById(id);
        try {
            if (document == null) {
                throw new FindByIdException("Doctor not found");
            }
            Document update = new Document("$set", updateDocument);
            dataManager.updateGenereal(id, update, "doctor");

        } catch (FindByIdException e) {
            throw new FindByIdException("Error finding doctor by ID", e);
        }
        return document;
    }


    @Override
    public  Optional<Boolean> existById(UUID id) {
        Document exist = dataManager.findByIdGeneral(id, getCollectionName());
        if (exist == null || exist.isEmpty()) {
            throw new IllegalStateException();
        }else{
            return Optional.of(true);
        }
    }


    public boolean existsDoctorByName(DoctorDTO doctorDTO) {
        Document existingSpecialty = dataManager.findDoctorByName(String.valueOf(doctorDTO.firstName()), getCollectionName());
        if (existingSpecialty == null || existingSpecialty.isEmpty()) {
        return false;
        }else{
            return true;
        }
    }

    public boolean existsMedicalSpecialtyByName(DoctorDTO doctorDTO) {
        Document existingSpecialty = dataManager.findEspecialtyByName(String.valueOf(doctorDTO.medicalSpecialty().getName()), "medicalSpecialty");
        if (existingSpecialty == null || existingSpecialty.isEmpty()) {
            throw new IllegalStateException("Nao existe uma especialidade com esse nome.");
        }else{
            return true;
        }
    }



}
