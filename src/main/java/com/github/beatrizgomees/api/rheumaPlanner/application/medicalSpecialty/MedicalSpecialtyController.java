package com.github.beatrizgomees.api.rheumaPlanner.application.medicalSpecialty;

import com.github.beatrizgomees.api.rheumaPlanner.domain.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicalSpecialty.MedicalSpecialtyRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.medicalSpecialtyService.MedicalSpecialtyServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/specialty")
@Tag(name = "specialty")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicalSpecialtyController {

    @Inject
    MedicalSpecialtyServiceImpl medicalSpecialtyService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctor(MedicalSpecialtyRequest medicalSpecialtyRequest){
        medicalSpecialtyService.create(medicalSpecialtyRequest);
        return Response.ok(medicalSpecialtyRequest).status(201).build();
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
    public Response findNoteById(@PathParam("id") String id)  throws FindByIdException {
        Document document;
        document = medicalSpecialtyService.findById(id);
        return Response.ok(document).status(200).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOneNote(@PathParam("id") String id){
        medicalSpecialtyService.delete(id);
        return Response.ok().status(200).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, Document document) throws FindByIdException {
        medicalSpecialtyService.update(id, document);
        return Response.ok(document).status(200).build();
    }



}
