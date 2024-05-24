package com.github.beatrizgomees.api.rheumaPlanner.service;

import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.service.medicalSpecialtyService.MedicalSpecialtyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;


@ExtendWith(MockitoExtension.class)
public class CreateMedicalSpecialtyTest {


    @Mock
    private DataManager dataManager;

    @Mock
    private MedicalSpecialtyServiceImpl medicalSpecialtyService;



    @Nested
    class createMedicalSpecialty {

        @Test
        @DisplayName("Should create a medical Specialty with sucess")
        void shouldCreateMedicalSpecialty() {

            var medicalSpecialty = new MedicalSpecialtyDTO(
                    "Reumato",
                    "Les"

            );

            //doReturn(medicalSpecialty).when(medicalSpecialtyService).create(userArgumentCaptor.capture());

            var input = new MedicalSpecialtyDTO(
                    "Reumatologia",
                    "Tratamento de Les"

            );

            var output  = medicalSpecialtyService.create(input);
            assertNotNull(output);

        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs() {

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
        void shouldThrowExceptionWhenCreateMedicalSpecialtyWithEmailNull() {
            var input = new MedicalSpecialtyDTO(
                    null,
                    "Tratamento de Les"

            );
            doThrow(new RuntimeException()).when(medicalSpecialtyService).create(input);

            //Act & Assert
            assertThrows(RuntimeException.class, () -> medicalSpecialtyService.create(input));
        }



    }


}
