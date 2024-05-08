package com.github.beatrizgomees.api.rheumaPlanner.service.medicineService;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.Doctor;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.GetException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.CrudService;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.Medicine;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineMapper;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class MedicineServiceImpl extends BaseCrudService<MedicineRequest, Document> {


    public MedicineServiceImpl(DataManager dataManager) {
        super(dataManager);
    }

    public MedicineServiceImpl(){

    }


    String[] fieldNames = getFieldNames(Medicine.class);

    @Override
    public String getCollectionName() {
        return "medicine";
    }

    @Override
    public MedicineRequest create(MedicineRequest request) {
        if (!existByName(request)) {
            MedicineMapper medicineMapper = new MedicineMapper();
            Medicine medicine = medicineMapper.toEntity(request);
            return request;
        } else {
            throw new IllegalStateException("Medicamento já está cadastrado.");
        }
    }
}
