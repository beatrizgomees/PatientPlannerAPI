package com.github.beatrizgomees.api.rheumaPlanner.domain;

import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudService<T, U> {

    T create(T request) throws IllegalAccessException;
    List<U> getAll();

    Optional<U> findById(UUID id) throws FindByIdException;

    void delete(UUID id);

    U update(String id, U updateDocument) throws FindByIdException;

    boolean existByName(T request);

    T convertRequestToDTO(U request);

}
