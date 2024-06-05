package com.github.beatrizgomees.api.rheumaPlanner.domain;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import org.bson.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudService<T, U> {

    T create(T request);
    List<U> getAll();

    Optional<U> findById(UUID id);

    void delete(UUID id);

    Optional<U> update(UUID id, U updateDocument);


    Optional<Boolean> existById(UUID id);


}
