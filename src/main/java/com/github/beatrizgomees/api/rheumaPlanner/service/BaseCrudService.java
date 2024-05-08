package com.github.beatrizgomees.api.rheumaPlanner.service;

import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import jakarta.inject.Inject;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BaseCrudService<T, R> implements CrudService<T, R> {

    @Inject
    DataManager dataManager;


    public BaseCrudService() {
    }

    protected <T> Document createDocument(T request, String[] fieldNames) {
        try {
            Document document = new Document();
            Class<?> clazz = request.getClass();

            for (String fieldName : fieldNames) {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(request);
                document.append(fieldName, value);
            }

            return document;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace(); // Ou lance uma exceção adequada
            return null;
        }
    }



    @Override
    public T create(T request) {
        Document document = createDocument(request, getFieldNames(request.getClass()));
        if (document != null) {
            dataManager.create(document, getCollectionName());
            return request;
        } else {
            throw new IllegalStateException("Falha ao criar documento.");
        }
    }


    @Override
    public List<R> getAll() {
        return null;
    }

    @Override
    public R findById(String id) throws FindByIdException {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public R update(String id, R updateDocument) throws FindByIdException {
        return null;
    }

    @Override
    public boolean existByName(T request) {
        Document exist = dataManager.findByNameGeneral(String.valueOf(request), getCollectionName());
        if (exist == null || exist.isEmpty()) {
            throw new IllegalStateException();
        }else{
            return true;
        }
    }


    protected String[] getFieldNames(Class<?> clazz) {
        List<String> fieldNamesList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldNamesList.add(field.getName());
        }
        return fieldNamesList.toArray(new String[0]);
    }


    public String getCollectionName() {
        return null;
    }


}
