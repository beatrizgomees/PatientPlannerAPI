package com.github.beatrizgomees.api.service;


import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineDTO;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.service.medicineService.MedicineServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicineServiceTest {

    @Mock
    private DataManager dataManager;

    @Mock
    private MedicineServiceImpl medicineService;

    @Captor
    private ArgumentCaptor<MedicineDTO> medicineArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> UUIDArgumentCaptor;


    @Nested
    class createMedicine {

        @Test
        @DisplayName("Should create a medicine with sucess")
        void shouldCreateMedicine() {
            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Reumatologia",
                    "Les"

            );

            var medicineDTO = new MedicineDTO(
                    UUID.randomUUID(),
                    "Azatioprina",
                    "imunosupressor",
                    2,
                    LocalDateTime.now(),
                    medicalSpecialty
            );

            doReturn(medicineDTO).when(medicineService).create(medicineArgumentCaptor.capture());


            var InputmedicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );
            var input = new MedicineDTO(
                    UUID.randomUUID(),
                    "Azatioprina",
                    "imunosupressor",
                    2,
                    LocalDateTime.now(),
                    InputmedicalSpecialty
            );

            var output = medicineService.create(input);
            assertNotNull(output);

            var medicineCaptor = medicineArgumentCaptor.getValue();

            assertEquals(input.id(), medicineCaptor.id());
            assertEquals(input.name(), medicineCaptor.name());
            assertEquals(input.medicalSpecialty(), medicineCaptor.medicalSpecialty());
        }

        @Test
        @DisplayName("Should throw exception when create medicine that alredy exist")
        void shouldThrowExceptionWhenCreatingMedicineAlreadyExists() {
            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Reumatologia",
                    "Les"

            );

            var medicineDTO = new MedicineDTO(
                    UUID.randomUUID(),
                    "Azatioprina",
                    "imunosupressor",
                    2,
                    LocalDateTime.now(),
                    medicalSpecialty
            );
            doThrow(new RuntimeException()).when(medicineService).create(medicineDTO);
            assertThrows(RuntimeException.class, () -> medicineService.create(medicineDTO));


        }

    }

    @Nested
    class getMedicineById{

        @Test
        @DisplayName("Should get medicine by id with sucess")
        void shouldGetMedicineById() {
            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Reumatologia",
                    "Les"

            );

            var medicineDTO = new MedicineDTO(
                    UUID.randomUUID(),
                    "Azatioprina",
                    "imunosupressor",
                    2,
                    LocalDateTime.now(),
                    medicalSpecialty
            );

            doReturn(Optional.of(medicineDTO)).when(medicineService).findById(UUIDArgumentCaptor.capture());

            var output = medicineService.findById(medicineDTO.id());
            assertNotNull(output);
            assertTrue(output.isPresent());
            assertEquals(medicineDTO.id(), UUIDArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("Should not get medicine if by id is not exist")
        void shouldGetMedicineByIdNotFound() {
            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Reumatologia",
                    "Les"

            );

            var medicineDTO = new MedicineDTO(
                    UUID.randomUUID(),
                    "Azatioprina",
                    "imunosupressor",
                    2,
                    LocalDateTime.now(),
                    medicalSpecialty
            );

            doReturn(Optional.empty()).when(medicineService).findById(UUIDArgumentCaptor.capture());

            var output = medicineService.findById(medicineDTO.id());

            assertTrue(output.isEmpty());
            assertEquals(medicineDTO.id(), UUIDArgumentCaptor.getValue());

        }

    }

    @Nested
    class listMedicines{

        @Test
        @DisplayName("should return all medicine with sucess")
        void shouldReturnAllMedicinesWithSucess() {

            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Reumatologia",
                    "Les"

            );

            var medicineDTO = new MedicineDTO(
                    UUID.randomUUID(),
                    "Azatioprina",
                    "imunosupressor",
                    2,
                    LocalDateTime.now(),
                    medicalSpecialty
            );

            var medicinesList = List.of(medicineDTO);

            doReturn(medicinesList).when(medicineService).getAll();

            var output = medicineService.getAll();
            assertNotNull(output);
            assertEquals(medicinesList.size(), output.size());

        }
    }

    @Nested
    class deleteMedicine{


        @Test
        void shouldDeleteMedicineByIdWithSUcess() {


            doNothing()
                    .when(medicineService)
                    .delete(UUIDArgumentCaptor.capture());

            var medicineId = UUID.randomUUID();

            // Act
            medicineService.delete(medicineId);

            // Assert
            var idList = UUIDArgumentCaptor.getAllValues();
            assertEquals(medicineId, idList.get(0));

        }
    }


}
