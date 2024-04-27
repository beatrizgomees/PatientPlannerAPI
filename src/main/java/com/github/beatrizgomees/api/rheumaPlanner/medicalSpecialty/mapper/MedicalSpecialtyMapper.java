package com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.mapper;

import com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.dto.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.medicalSpecialty.entity.MedicalSpecialty;

public class MedicalSpecialtyMapper {

    public MedicalSpecialty toEntity(MedicalSpecialtyRequest medicalSpecialtyRequest){
        MedicalSpecialty medicalSpecialty = new MedicalSpecialty();
        medicalSpecialty.setName(medicalSpecialtyRequest.name());
        medicalSpecialty.setDescription(medicalSpecialtyRequest.description());
        return medicalSpecialty;
    }
}
