package com.github.beatrizgomees.api.rheumaPlanner.service.medicineService;


import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.BaseCrudService;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;


@ApplicationScoped
public class MedicineServiceImpl extends BaseCrudService<MedicineDTO, MedicineRequest> {


    public MedicineServiceImpl() {

    }

    @Override
    public String getCollectionName() {
        return "medicine";
    }

    @Override
    public MedicineDTO convertRequestToDTO(MedicineRequest request) {
        MedicineMapper mapper = new MedicineMapper();
        return mapper.convertRequestToDTO(request);
    }


}
