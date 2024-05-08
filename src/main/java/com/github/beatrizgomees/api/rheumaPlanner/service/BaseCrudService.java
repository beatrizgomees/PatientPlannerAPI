package com.github.beatrizgomees.api.rheumaPlanner.service;

import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import jakarta.inject.Inject;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BaseCrudService<T, R> implements CrudService<T, R> {

    @Inject
    DataManager dataManager;

    public BaseCrudService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public BaseCrudService() {
    }


    public R createDocument(T request, String[] fieldNames) throws IllegalAccessException {
        Document document = new Document();

        // Obter a classe do objeto de solicitação
        Class<?> clazz = request.getClass();

        // Iterar sobre os nomes dos campos fornecidos
        for (String fieldName : fieldNames) {
            try {
                // Obter o campo da classe
                Field field = clazz.getDeclaredField(fieldName);

                // Tornar o campo acessível (se necessário)
                field.setAccessible(true);

                // Obter o valor do campo do objeto de solicitação
                Object value = field.get(request);

                // Adicionar o campo e seu valor ao documento MongoDB
                document.append(fieldName, value);
            } catch (NoSuchFieldException e) {
                // Lidar com o caso em que o campo não é encontrado
                // Aqui você pode lançar uma exceção, registrar o erro ou lidar de outra forma
                // Exemplo: throw new IllegalArgumentException("Campo não encontrado: " + fieldName, e);
            }
        }

        // Retorna o documento criado
        return (R) document;
    }


    @Override
    public T create(T request) throws IllegalAccessException {
        Document document = (Document) createDocument(request, getFieldNames(getClass()));
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
