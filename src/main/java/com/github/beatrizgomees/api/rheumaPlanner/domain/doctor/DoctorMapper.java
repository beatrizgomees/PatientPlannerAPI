package com.github.beatrizgomees.api.rheumaPlanner.domain.doctor;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.Doctor;
import org.bson.Document;

public class DoctorMapper {

    public Doctor toEntity(DoctorRequest doctorRequest){
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequest.name());
        doctor.setDescription(doctorRequest.description());
        doctor.setLastname(doctorRequest.lastname());
        doctor.setMedicalSpecialty(doctorRequest.medicalSpecialty());
        doctor.setPhoneNumber(doctorRequest.phoneNumber());
        return doctor;
    }

    public Document toDocument(DoctorRequest doctorRequest) {
        return new Document()
                .append("name", doctorRequest.name())
                .append("description", doctorRequest.description())
                .append("lastName", doctorRequest.lastname())
                .append("medicalSpecialty", doctorRequest.medicalSpecialty())
                .append("phoneNumber", doctorRequest.phoneNumber());
    }
}
