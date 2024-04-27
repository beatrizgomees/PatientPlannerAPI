package com.github.beatrizgomees.api.rheumanotes.medicalSpecialty.mapper;

import com.github.beatrizgomees.api.rheumanotes.doctor.dto.DoctorRequest;
import com.github.beatrizgomees.api.rheumanotes.doctor.entity.Doctor;
import com.github.beatrizgomees.api.rheumanotes.medicalSpecialty.dto.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumanotes.medicalSpecialty.entity.MedicalSpecialty;

public class MedicalSpecialtyMapper {

    public MedicalSpecialty toEntity(MedicalSpecialtyRequest medicalSpecialtyRequest){
        MedicalSpecialty medicalSpecialty = new MedicalSpecialty();
        medicalSpecialty.setName(medicalSpecialtyRequest.name());
        medicalSpecialty.setDescription(medicalSpecialtyRequest.description());
        return medicalSpecialty;
    }
}
