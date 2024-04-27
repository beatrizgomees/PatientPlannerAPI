package com.github.beatrizgomees.api.rheumaPlanner.doctor.mapper;

import com.github.beatrizgomees.api.rheumaPlanner.doctor.dto.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.doctor.entity.Doctor;

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
}
