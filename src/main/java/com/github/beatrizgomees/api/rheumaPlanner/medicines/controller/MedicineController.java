package com.github.beatrizgomees.api.rheumaPlanner.medicines.controller;

import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.medicines.dto.MedicineRequest;
import com.github.beatrizgomees.api.rheumaPlanner.medicines.service.MedicineServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/medicines")
@Tag(name = "medicines")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicineController {

    @Inject
    MedicineServiceImpl medicineService;

    public MedicineController(MedicineServiceImpl medicineService) {
        this.medicineService = medicineService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctor(MedicineRequest medicineRequest){
       medicineService.create(medicineRequest);
        return Response.ok(medicineRequest).status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(){
        List<Document> documentList = medicineService.getAll();
        return Response.ok(documentList).status(200).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findNoteById(@PathParam("id") String id)  throws FindByIdException {
        Document document;
        document = medicineService.findById(id);
        return Response.ok(document).status(200).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOneNote(@PathParam("id") String id){
        medicineService.delete(id);
        return Response.ok().status(200).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, Document document) throws FindByIdException {
        medicineService.update(id, document);
        return Response.ok(document).status(200).build();
    }
}
