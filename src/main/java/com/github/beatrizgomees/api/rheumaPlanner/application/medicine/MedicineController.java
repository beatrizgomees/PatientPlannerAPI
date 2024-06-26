package com.github.beatrizgomees.api.rheumaPlanner.application.medicine;

import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorRequest;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineMapper;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.medicine.MedicineRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.medicineService.MedicineServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        MedicineMapper mapper = new MedicineMapper();
        MedicineDTO medicineDTO = mapper.convertRequestToDTO(medicineRequest);
        medicineService.create(medicineDTO);
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
    public Response findNoteById(@PathParam("id") UUID id)  throws FindByIdException {
        Optional<Document> medicineRequest =  medicineService.findById(id);
        return Response.ok(medicineRequest).status(200).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOneNote(@PathParam("id") UUID id){
        medicineService.delete(id);
        return Response.ok().status(200).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, MedicineRequest medicineRequest) throws FindByIdException {
        MedicineMapper mapper = new MedicineMapper();
        MedicineDTO medicineDTO = mapper.convertRequestToDTO(medicineRequest);
        Document document = mapper.convertDtoToDocument(medicineDTO);
        medicineService.update(UUID.fromString(id), document);
        return Response.ok(medicineRequest).status(200).build();
    }
}
