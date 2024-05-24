package com.github.beatrizgomees.api.rheumaPlanner.service.medicalSpecialtyService;


import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;

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
