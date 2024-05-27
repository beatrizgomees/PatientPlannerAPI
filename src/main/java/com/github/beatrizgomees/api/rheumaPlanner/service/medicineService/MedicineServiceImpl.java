package com.github.beatrizgomees.api.rheumaPlanner.service.medicineService;


import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineRequest;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class MedicineServiceImpl implements CrudService<MedicineDTO, Document> {


    public MedicineServiceImpl() {

    }




    @Override
    public MedicineDTO create(MedicineDTO request) {
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
