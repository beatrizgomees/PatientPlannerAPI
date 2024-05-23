package com.github.beatrizgomees.api.rheumaPlanner.application.doctor;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorResponse;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.doctorService.DoctorServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/doctor")
@Tag(name = "doctor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoctorController {
    @Inject
    DoctorServiceImpl doctorServiceImpl;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctor(DoctorRequest doctorRequest) {
        DoctorDTO doctorDTO = doctorServiceImpl.convertRequestToDTO(doctorRequest);
        doctorServiceImpl.create(doctorDTO);
        return Response.ok(doctorDTO).status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(){
        List<DoctorRequest> documentList = doctorServiceImpl.getAll();
        return Response.ok(documentList).status(200).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findNoteById(@PathParam("id") String id){
        DoctorRequest doctorRequest = doctorServiceImpl.findById(id);
        return Response.ok(doctorRequest).status(200).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOneNote(@PathParam("id") String id){
        doctorServiceImpl.delete(id);
        return Response.ok().status(200).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, DoctorRequest doctorRequest) throws FindByIdException {
        doctorServiceImpl.update(id, doctorRequest);
        return Response.ok(doctorRequest).status(200).build();
    }

}
