package com.github.beatrizgomees.api.rheumaPlanner.domain.doctor;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.Doctor;

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
