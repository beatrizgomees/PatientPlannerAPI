package com.github.beatrizgomees.api.rheumaPlanner.domain.medicine;


import org.bson.Document;
import org.mapstruct.Mapper;

@Mapper
public class MedicineMapper {

    public Medicine convertDtoToEntity(MedicineDTO medicineDTO){
        Medicine medicine = new Medicine();
        medicine.setName(medicineDTO.name());
        medicine.setAmount(medicineDTO.amount());
        medicine.setReminder(medicineDTO.reminder());
        medicine.setDescription(medicineDTO.description());
        return medicine;
    }


    public Document convertDtoToDocument(MedicineDTO medicineDTO) {
        if (medicineDTO == null) {
            return null;
        }

        return new Document()
                .append("name", medicineDTO.name())
                .append("amount", medicineDTO.amount())
                .append("reminder", medicineDTO.reminder())
                .append("description", medicineDTO.description());

    }

    public MedicineResponse convertEntityToResponse(Medicine medicine) {
        if (medicine == null) {
            return null;
        }
        MedicineResponse medicineResponse = new MedicineResponse(
                medicine.getId(),
                medicine.getName(),
                medicine.getDescription(),
                medicine.getAmount(),
                medicine.getReminder(),
                medicine.getMedicalSpecialty()
        );
        return medicineResponse;

    }
    public MedicineDTO convertRequestToDTO(MedicineRequest medicineRequest) {
        if (medicineRequest == null) {
            return null;
        }
        return new MedicineDTO(
                medicineRequest.id(),
                medicineRequest.name(),
                medicineRequest.description(),
                medicineRequest.amount(),
                medicineRequest.reminder(),
                medicineRequest.medicalSpecialty()
        );
    }


}
