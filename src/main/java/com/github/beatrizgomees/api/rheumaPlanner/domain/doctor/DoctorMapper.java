package com.github.beatrizgomees.api.rheumaPlanner.domain.doctor;

import org.bson.Document;

public class DoctorMapper {

    public Doctor convertDtoToEntity(DoctorDTO doctorDTO){
        if (doctorDTO == null) {
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setFirstname(doctorDTO.firstName());
        doctor.setDescription(doctorDTO.description());
        doctor.setLastName(doctorDTO.lastName());
        doctor.setMedicalSpecialty(doctor.getMedicalSpecialty());
        doctor.setPhoneNumber(doctorDTO.phoneNumber());
        return doctor;
    }

    public Document convertDtoToDocument(DoctorDTO doctorDTO) {
        if (doctorDTO == null) {
            return null;
        }

        return new Document()
                .append("name", doctorDTO.firstName())
                .append("description", doctorDTO.description())
                .append("lastName", doctorDTO.lastName())
                .append("medicalSpecialty", doctorDTO.medicalSpecialty())
                .append("phoneNumber", doctorDTO.phoneNumber());
    }


    public DoctorDTO convertRequestToDTO(DoctorRequest doctorRequest) {
        if (doctorRequest == null) {
            return null;
        }


        return new DoctorDTO(
                doctorRequest.id(),
                doctorRequest.firstName(),
                doctorRequest.lastname(),
                doctorRequest.medicalSpecialty(),
                doctorRequest.description(),
                doctorRequest.phoneNumber()
        );
    }
    public DoctorResponse convertEntityToResponse(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        return new DoctorResponse(

                doctor.getId(),
                doctor.getFirstname(),
                doctor.getLastName(),
                doctor.getDescription(),
                doctor.getMedicalSpecialty(),
                doctor.getPhoneNumber()
        );

    }
}
