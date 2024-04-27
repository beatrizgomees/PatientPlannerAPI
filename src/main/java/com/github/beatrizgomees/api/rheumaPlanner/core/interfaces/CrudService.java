package com.github.beatrizgomees.api.rheumaPlanner.core.interfaces;

import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.FindByIdException;

import java.util.List;

public interface CrudService<T, U> {

    T create(T request);
    List<U> getAll();

    U findById(String id) throws FindByIdException;

    void delete(String id);

    U update(String id, U updateDocument) throws FindByIdException;


     boolean existsMedicalSpecialtyByName(T request);


}
