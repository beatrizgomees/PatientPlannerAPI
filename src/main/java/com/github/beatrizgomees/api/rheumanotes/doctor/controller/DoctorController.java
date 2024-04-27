package com.github.beatrizgomees.api.rheumanotes.doctor.controller;

import com.github.beatrizgomees.api.rheumanotes.core.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumanotes.doctor.dto.DoctorRequest;
import com.github.beatrizgomees.api.rheumanotes.doctor.service.DoctorServiceImpl;
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
    public Response createDoctor(DoctorRequest doctorRequest){
        DoctorRequest doctor = doctorServiceImpl.create(doctorRequest);
        return Response.ok(doctor).status(201).build();
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
        try {
            document = doctorServiceImpl.findById(id);
        } catch (FindByIdException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
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
