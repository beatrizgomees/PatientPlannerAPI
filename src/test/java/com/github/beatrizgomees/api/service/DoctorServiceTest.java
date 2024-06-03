package com.github.beatrizgomees.api.service;


import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.service.doctorService.DoctorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    DataManager dataManager;
    @Mock
    private DoctorServiceImpl doctorService;

    @Captor
    private ArgumentCaptor<DoctorDTO> doctorDTOArgumentCaptor;



    @Nested
    class createDoctor{
        @Test
        void shouldCreateDoctorWithSucess() {

            var medicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"
            );

            var doctorDto = new DoctorDTO(
                    UUID.randomUUID(),
                    "BEA",
                    "gomes",
                    medicalSpecialty,
                    "819964523",
                    "Meu reumatologista do HC"
            );



            doReturn(doctorDto).when(doctorService).create(doctorDTOArgumentCaptor.capture());

            var InputmedicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );

            var input = new DoctorDTO(
                    UUID.randomUUID(),
                    "BEA",
                    "gomes",
                    InputmedicalSpecialty,
                    "819964523",
                    "Meu reumatologista do HC"
            );

            var output = doctorService.create(input);
            assertNotNull(output);

            var doctorCaptor = doctorDTOArgumentCaptor.getValue();
            assertEquals(input.firstName(), doctorCaptor.firstName());
            assertEquals(input.lastName(), doctorCaptor.lastName());
            assertEquals(input.medicalSpecialty().name(), doctorCaptor.medicalSpecialty().name());
        }


        @Test
        @DisplayName("Should throw exception when create a doctor error occurs")
        void shouldThrowExceptionWhenTryCreateDcotorWithNamesEquals() {
            var InputmedicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );

            var input = new DoctorDTO(
                    UUID.randomUUID(),
                    "BEA",
                    "gomes",
                    InputmedicalSpecialty,
                    "819964523",
                    "Meu reumatologista do HC"
            );

            doThrow(new RuntimeException()).when(doctorService).create(input);

            assertThrows(RuntimeException.class, () -> doctorService.create(input));
            
        }

        @Test
        void shouldThrowExceptiuonWhenCreateDoctorWithNameNull() {
            var InputmedicalSpecialty = new MedicalSpecialtyDTO(
                    UUID.randomUUID(),
                    "Lupus",
                    "Les"

            );

            var input = new DoctorDTO(
                    UUID.randomUUID(),
                    "BEA",
                    "gomes",
                    InputmedicalSpecialty,
                    "819964523",
                    "Meu reumatologista do HC"
            );

            doThrow(new NullPointerException()).when(doctorService).create(input);
            assertThrows(NullPointerException.class, () -> doctorService.create(input));

        }
    }


}
