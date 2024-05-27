package com.github.beatrizgomees.api.rheumaPlanner.application.medicalSpecialty;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyMapper;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.medicalSpecialtyService.MedicalSpecialtyServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/specialty")
@Tag(name = "specialty")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicalSpecialtyController {

    @Inject
    MedicalSpecialtyServiceImpl medicalSpecialtyService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMedicalSpecialty(MedicalSpecialtyRequest medicalSpecialtyRequest){
        try {
            if (medicalSpecialtyRequest.name() == null) {
                throw new NullPointerException("Name cannot be null");
            }
            MedicalSpecialtyMapper medicalSpecialtyMapper = new MedicalSpecialtyMapper();
            var dto = medicalSpecialtyMapper.convertRequestToDTO(medicalSpecialtyRequest);
            medicalSpecialtyService.create(dto);

            return Response.ok(medicalSpecialtyRequest).status(201).build();
        } catch (NullPointerException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(){
        List<Document> documentList = medicalSpecialtyService.getAll();
        return Response.ok(documentList).status(200).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findNoteById(@PathParam("id") UUID id)  throws FindByIdException {
        Optional<Document> medicalSpecialtyRequest = medicalSpecialtyService.findById(id);
        return Response.ok(medicalSpecialtyRequest).status(200).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOneNote(@PathParam("id") UUID id){
        medicalSpecialtyService.delete(id);
        return Response.ok().status(200).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, MedicalSpecialtyRequest medicalSpecialtyRequest) throws FindByIdException {
        MedicalSpecialtyMapper mapper = new MedicalSpecialtyMapper();
        MedicalSpecialtyDTO medicalSpecialtyDTO = mapper.convertRequestToDTO(medicalSpecialtyRequest);
        Document document = mapper.convertDtoToDocument(medicalSpecialtyDTO);
        medicalSpecialtyService.update(UUID.fromString(id),document);
        return Response.ok(medicalSpecialtyRequest).status(200).build();
    }



}
