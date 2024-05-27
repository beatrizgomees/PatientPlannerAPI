package com.github.beatrizgomees.api.service;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialty;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.service.medicalSpecialtyService.MedicalSpecialtyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MedicalSpecialtyTest {


    @Mock
    private DataManager dataManager;

    @Mock
    private MedicalSpecialtyServiceImpl medicalSpecialtyService;

    @Captor
    private ArgumentCaptor<MedicalSpecialtyDTO> medicalSpecialtyArgumentCaptor;
    @Captor
    private ArgumentCaptor<UUID> UUIDArgumentCaptor;

    @Nested
    class createMedicalSpecialty {

        @Test
        @DisplayName("Should create a medical Specialty with sucess")
        void shouldCreateMedicalSpecialty() {

            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );
            doReturn(medicalSpecialty).when(medicalSpecialtyService).create(medicalSpecialtyArgumentCaptor.capture());
            var input = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Tratamento de Les"

            );

            var output  = medicalSpecialtyService.create(input);
            assertNotNull(output);

            var medicalSpecialCaptor = medicalSpecialtyArgumentCaptor.getValue();

            assertEquals(input.name(), medicalSpecialCaptor.name());
            assertEquals(medicalSpecialty.name(), medicalSpecialCaptor.name());
            assertEquals(input.description(), medicalSpecialCaptor.description());

        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs() throws IllegalAccessException {

            var input = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Reumatologia",
                    "Tratamento de Les"

            );
            doThrow(new RuntimeException()).when(medicalSpecialtyService).create(input);

            //Act & Assert
            assertThrows(RuntimeException.class, () -> medicalSpecialtyService.create(input));

        }

        @Test
        @DisplayName("Should throw exception when create medical specialty with email null")
        void shouldThrowExceptionWhenCreateMedicalSpecialtyWithEmailNull() throws IllegalAccessException {
            var input = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Reumatologia",
                    "Tratamento de Les"

            );
            doThrow(new RuntimeException()).when(medicalSpecialtyService).create(input);

            //Act & Assert
            assertThrows(RuntimeException.class, () -> medicalSpecialtyService.create(input));
        }



    }

    @Nested
    class getMedicalSpecialtyById{


        @Test
        @DisplayName("Should get medical specialty by id with sucess when optional is present")
        void shouldGetMedicalSpecialtyByIdWithSuccessWhenOptionalIsPresent() {
            //Arrange

            var medicalSpecialty = new MedicalSpecialty(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );

            doReturn(Optional.of(medicalSpecialty)).when(medicalSpecialtyService).findById(UUIDArgumentCaptor.capture());

            //Act
            var output = medicalSpecialtyService.findById(medicalSpecialty.getId());

            //Assert
            assertTrue(output.isPresent());
            assertEquals(medicalSpecialty.getId(), UUIDArgumentCaptor.getValue());
        }


        @Test
        @DisplayName("Should get medical specialty by id with sucess when optional is present")
        void shouldGetMedicalSpecialtyByIdWhenOptionalIsEmpty() {
            var medicalSpecialty = new MedicalSpecialty(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );

            doReturn(Optional.empty()).when(medicalSpecialtyService).findById(UUIDArgumentCaptor.capture());

            //Act
            var output = medicalSpecialtyService.findById(medicalSpecialty.getId());

            //Assert
            assertTrue(output.isEmpty());
            assertEquals(medicalSpecialty.getId(), UUIDArgumentCaptor.getValue());
        }
    }

    @Nested
    class listMedicalSpecialtys {

        @Test
        @DisplayName("Should return all medical specialtys with sucess")
        void shouldReturnAllMedicalSpecialtysWithSucess() {
            //Arrange
            var medicalSpecialty = new MedicalSpecialty(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );
            var medicalSpecialList = List.of(medicalSpecialty);
            doReturn(medicalSpecialList).when(medicalSpecialtyService).getAll();

            //Act
            var output = medicalSpecialtyService.getAll();
            //Assert
            assertNotNull(output);
            assertEquals(medicalSpecialList.size(), output.size());

        }
    }

    @Nested
    class deleteMedicalSpecialtyById{

        @Test
        @DisplayName("Should delete medical specialty with sucess when medical specialty exists")
        void shouldDeleteMedicalSpecialtyByIdWithSuccessWhenOptionalIsPresent(){
            //Arrange
            //Why doReturn not run?

            doNothing()
                    .when(medicalSpecialtyService)
                    .delete(UUIDArgumentCaptor.capture());

            var medicalSpecialtyId = UUID.randomUUID();

            // Act
            medicalSpecialtyService.delete(medicalSpecialtyId);

            // Assert
            var idList = UUIDArgumentCaptor.getAllValues();
            assertEquals(medicalSpecialtyId, idList.get(0));

        }
    }
}
