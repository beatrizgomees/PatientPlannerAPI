package com.github.beatrizgomees.api.rheumanotes.core.interfaces;

import com.github.beatrizgomees.api.rheumanotes.core.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumanotes.notes.dto.NoteRequest;

import java.util.List;

public interface CrudService<T, U> {

    T create(T request);
    List<U> getAll();

    U findById(String id) throws FindByIdException;

    void delete(String id);

    U update(String id, U updateDocument) throws FindByIdException;


     boolean existsMedicalSpecialtyByName(T request);


}
