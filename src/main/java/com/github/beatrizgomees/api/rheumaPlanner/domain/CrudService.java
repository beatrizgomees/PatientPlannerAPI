package com.github.beatrizgomees.api.rheumaPlanner.domain;

import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.FindByIdException;

import java.util.List;

public interface CrudService<T, U> {

    T create(T request) throws IllegalAccessException;
    List<U> getAll();

    U findById(String id) throws FindByIdException;

    void delete(String id);

    U update(String id, U updateDocument) throws FindByIdException;


     boolean existByName(T request);


}
