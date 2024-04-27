package com.github.beatrizgomees.api.rheumanotes.doctor.mapper;

import com.github.beatrizgomees.api.rheumanotes.doctor.dto.DoctorRequest;
import com.github.beatrizgomees.api.rheumanotes.doctor.entity.Doctor;
import com.github.beatrizgomees.api.rheumanotes.notes.dto.NoteRequest;
import com.github.beatrizgomees.api.rheumanotes.notes.entity.Note;

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
