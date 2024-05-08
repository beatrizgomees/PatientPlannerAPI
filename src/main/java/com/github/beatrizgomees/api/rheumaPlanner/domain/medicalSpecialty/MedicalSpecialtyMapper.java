package com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;

public class MedicalSpecialtyMapper {

    public MedicalSpecialty toEntity(MedicalSpecialtyRequest medicalSpecialtyRequest){
        MedicalSpecialty medicalSpecialty = new MedicalSpecialty();
        medicalSpecialty.setName(medicalSpecialtyRequest.name());
        medicalSpecialty.setDescription(medicalSpecialtyRequest.description());
        return medicalSpecialty;
    }
}
