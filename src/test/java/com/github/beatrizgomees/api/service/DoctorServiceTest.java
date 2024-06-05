package com.github.beatrizgomees.api.service;


import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.data.DataManager;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.service.doctorService.DoctorServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    DataManager dataManager;

    @Mock
    private DoctorServiceImpl doctorService;

    private DoctorServiceImpl doctorServiceWithDataManagerMock = new DoctorServiceImpl(dataManager);

    @Captor
    private ArgumentCaptor<DoctorDTO> doctorDTOArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> UUIDArgumentCaptor;




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


    @Nested
    class getDoctorById{

        @Test
        @DisplayName("Should get doctor by id with sucess when optional is present")
        public void shouldGetDoctorById() {
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

            doReturn(Optional.of(input)).when(doctorService).findById(UUIDArgumentCaptor.capture());

            var output = doctorService.findById(input.id());

            assertTrue(output.isPresent());
            assertEquals(input.id(),UUIDArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("Should not get doctor by id with sucess when optional is empty")
        public void shouldGetDoctorByIdWhenOptionalIsEmpty(){
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

            doReturn(Optional.empty()).when(doctorService).findById(UUIDArgumentCaptor.capture());
            var output = doctorService.findById(input.id());

            assertFalse(output.isPresent());
            assertEquals(input.id(),UUIDArgumentCaptor.getValue());
        }

    }

    @Nested
    class listDoctor{


        @Test
        @DisplayName("Should return all doctor with sucess")
        public void shouldReturnAllMedicalSpecialtysWithSucess(){
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

            var doctorList = List.of(input);
            doReturn(doctorList).when(doctorService).getAll();

            var output = doctorService.getAll();
            assertNotNull(output);
            assertEquals(doctorList.size(), output.size());

        }

    }

    @Nested
    class deleteDoctorById{

        @Test
        @DisplayName("Should delete doctor with sucess when medical specialty exists")
        public void deleteDoctorById()  {

            doNothing().when(doctorService).delete(UUIDArgumentCaptor.capture());

            var doctorId = UUID.randomUUID();

            doctorService.delete(doctorId);

            var idList = UUIDArgumentCaptor.getAllValues();
            assertEquals(doctorId, idList.get(0));

        }



    }


}
