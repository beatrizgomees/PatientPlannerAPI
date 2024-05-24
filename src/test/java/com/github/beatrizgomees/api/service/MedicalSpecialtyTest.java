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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;


@ExtendWith(MockitoExtension.class)
public class MedicalSpecialtyTest {


    @Mock
    private DataManager dataManager;

    @Mock
    private MedicalSpecialtyServiceImpl medicalSpecialtyService;

    @Captor
    private ArgumentCaptor<MedicalSpecialtyDTO> medicalSpecialtyArgumentCaptor;
    @Captor
    private ArgumentCaptor<UUID> UUIDStringArgumentCaptor;

    @Nested
    class createMedicalSpecialty {

        @Test
        @DisplayName("Should create a medical Specialty with sucess")
        void shouldCreateMedicalSpecialty() {

            var medicalSpecialty = new MedicalSpecialtyDTO(
                    "Lupus",
                    "Les"

            );
            doReturn(medicalSpecialty).when(medicalSpecialtyService).create(medicalSpecialtyArgumentCaptor.capture());
            var input = new MedicalSpecialtyDTO(
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
                    null,
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

            doReturn(Optional.of(medicalSpecialty)).when(medicalSpecialtyService).findById(UUIDStringArgumentCaptor.capture());

            //Act
            var output = medicalSpecialtyService.findById(medicalSpecialty.getId());

            //Assert
            assertTrue(output.isPresent());
            assertEquals(medicalSpecialty.getId(), UUIDStringArgumentCaptor.getValue());
        }


        @Test
        @DisplayName("Should get medical specialty by id with sucess when optional is present")
        void shouldGetMedicalSpecialtyByIdWhenOptionalIsEmpty() {
            var medicalSpecialty = new MedicalSpecialty(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );

            doReturn(Optional.empty()).when(medicalSpecialtyService).findById(UUIDStringArgumentCaptor.capture());

            //Act
            var output = medicalSpecialtyService.findById(medicalSpecialty.getId());

            //Assert
            assertTrue(output.isEmpty());
            assertEquals(medicalSpecialty.getId(), UUIDStringArgumentCaptor.getValue());
        }
    }
}
