package com.github.beatrizgomees.api.rheumaPlanner.domain.medicine;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.Medicine;

public class MedicineMapper {

    public Medicine toEntity(MedicineRequest medicineRequest){
        Medicine medicine = new Medicine();
        medicine.setName(medicineRequest.name());
        medicine.setAmount(medicineRequest.amount());
        medicine.setReminder(medicineRequest.reminder());
        medicine.setDescription(medicineRequest.description());
        return medicine;
    }
}
