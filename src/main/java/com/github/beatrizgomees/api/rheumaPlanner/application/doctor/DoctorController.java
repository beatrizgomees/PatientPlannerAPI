package com.github.beatrizgomees.api.rheumaPlanner.application.doctor;

import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.doctorService.DoctorServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
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
    public Response createDoctor(DoctorRequest doctorRequest) throws IllegalAccessException {
        doctorServiceImpl.create(doctorRequest);
        return Response.ok(doctorRequest).status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(){
        List<Document> documentList = doctorServiceImpl.getAll();
        return Response.ok(documentList).status(200).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findNoteById(@PathParam("id") String id)  throws FindByIdException {
        Document document;
        document = doctorServiceImpl.findById(id);
        return Response.ok(document).status(200).build();

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
    public Response updateNote(@PathParam("id") String id, Document document) throws FindByIdException {
        doctorServiceImpl.update(id, document);
        return Response.ok(document).status(200).build();
    }

}
