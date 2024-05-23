package com.github.beatrizgomees.api.rheumaPlanner.service.medicalSpecialtyService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

@ApplicationScoped
public class MedicalSpecialtyServiceImpl extends BaseCrudService<MedicalSpecialtyDTO, MedicalSpecialtyRequest> {


    public MedicalSpecialtyServiceImpl() {

    }

    @Override
    public String getCollectionName() {
        return "medicalSpecialty";
    }

    @Override
    public MedicalSpecialtyDTO convertRequestToDTO(MedicalSpecialtyRequest request) {
        MedicalSpecialtyMapper mapper = new MedicalSpecialtyMapper();
        return mapper.convertRequestToDTO(request);
    }
}
