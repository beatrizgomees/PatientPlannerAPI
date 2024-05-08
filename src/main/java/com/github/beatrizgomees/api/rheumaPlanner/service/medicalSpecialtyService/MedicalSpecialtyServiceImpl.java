package com.github.beatrizgomees.api.rheumaPlanner.service.medicalSpecialtyService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.Doctor;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.GetException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyMapper;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class MedicalSpecialtyServiceImpl extends BaseCrudService<MedicalSpecialtyRequest, Document> {


    public MedicalSpecialtyServiceImpl() {
    }


    @Override
    public String getCollectionName() {
        return "medicalSpecialty";
    }





}
