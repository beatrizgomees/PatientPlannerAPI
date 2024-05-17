package com.github.beatrizgomees.api.rheumaPlanner.service.medicalSpecialtyService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

@ApplicationScoped
public class MedicalSpecialtyServiceImpl extends BaseCrudService<MedicalSpecialtyRequest, Document> {


    public MedicalSpecialtyServiceImpl() {
    }


    @Override
    public String getCollectionName() {
        return "medicalSpecialty";
    }





}
