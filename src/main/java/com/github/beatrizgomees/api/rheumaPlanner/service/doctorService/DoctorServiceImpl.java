package com.github.beatrizgomees.api.rheumaPlanner.service.doctorService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.*;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class DoctorServiceImpl extends BaseCrudService<DoctorDTO, DoctorRequest> {



    public DoctorServiceImpl() {
    }

    @Override
    public String getCollectionName() {
        return "doctor";
    }

    @Override
    protected String[] getFieldNames(Class<?> clazz) {
        return new String[] { "firstName", "lastName", "description", "medicalSpecialty", "phoneNumber" };
    }

    @Override
    public DoctorDTO convertRequestToDTO(DoctorRequest request) {
        DoctorMapper mapper = new DoctorMapper();
        return mapper.convertRequestToDTO(request);
    }



}
