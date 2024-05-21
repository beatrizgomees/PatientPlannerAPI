package com.github.beatrizgomees.api.rheumaPlanner.service.doctorService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.Doctor;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;



@ApplicationScoped
public class DoctorServiceImpl extends BaseCrudService<DoctorRequest, Document> {


    public DoctorServiceImpl() {
    }

    @Override
    public String getCollectionName() {
        return "doctor";
    }

    @Override
    protected String[] getFieldNames(Class<?> clazz) {
        return new String[] { "name", "lastname", "description", "medicalSpecialty", "phoneNumber" };
    }


}
