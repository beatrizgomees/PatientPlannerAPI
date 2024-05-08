package com.github.beatrizgomees.api.rheumaPlanner.service.doctorService;

import com.github.beatrizgomees.api.rheumaPlanner.application.doctor.DoctorController;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.Doctor;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;



@ApplicationScoped
public class DoctorServiceImpl extends BaseCrudService<DoctorRequest, Document> {


    public DoctorServiceImpl(DataManager dataManager) {
        super(dataManager);
    }

    public DoctorServiceImpl() {

    }


    String[] fieldNames = getFieldNames(Doctor.class);

    @Override
    public String getCollectionName() {
        return "doctor";
    }

    @Override
    public DoctorRequest create(DoctorRequest request) throws IllegalAccessException {
        if (!existByName(request)) {
            DoctorMapper doctorMapper = new DoctorMapper();
            Doctor doctor = doctorMapper.toEntity(request);
            createDocument(request, fieldNames);
            return request;
        } else {
            throw new IllegalStateException("Médico já está cadastrado.");
        }
    }
}
