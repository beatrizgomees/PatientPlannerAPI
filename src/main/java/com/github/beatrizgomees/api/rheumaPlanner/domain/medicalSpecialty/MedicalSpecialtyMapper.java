package com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.Doctor;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorResponse;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;
import org.bson.Document;

public class MedicalSpecialtyMapper {

    public MedicalSpecialty convertDtoToEntity(MedicalSpecialtyDTO medicalSpecialtyDTO){
        if (medicalSpecialtyDTO == null) {
            return null;
        }
        MedicalSpecialty medicalSpecialty = new MedicalSpecialty();
        medicalSpecialty.setName(medicalSpecialtyDTO.name());
        medicalSpecialty.setDescription(medicalSpecialtyDTO.description());
        return medicalSpecialty;
    }


    public Document convertDtoToDocument(MedicalSpecialtyDTO medicalSpecialtyDTO) {
        if (medicalSpecialtyDTO == null) {
            return null;
        }

        return new Document()
                .append("name", medicalSpecialtyDTO.name())
                .append("description", medicalSpecialtyDTO.description());

    }

    public MedicalSpecialtyResponse convertEntityToResponse(MedicalSpecialty medicalSpecialty) {
        if (medicalSpecialty == null) {
            return null;
        }
        MedicalSpecialtyResponse medicalSpecialtyResponse = new MedicalSpecialtyResponse(
                medicalSpecialty.getId(),
                medicalSpecialty.getName(),
                medicalSpecialty.getDescription()

        );
        return medicalSpecialtyResponse;

    }
    public MedicalSpecialtyDTO convertRequestToDTO(MedicalSpecialtyRequest medicalSpecialtyRequest) {
        if (medicalSpecialtyRequest == null) {
            return null;
        }
        return new MedicalSpecialtyDTO(
                medicalSpecialtyRequest.name(),
                medicalSpecialtyRequest.description()
        );
    }
}








