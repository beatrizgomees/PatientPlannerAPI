package com.github.beatrizgomees.api.rheumaPlanner.application.note;


import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.doctor.DoctorMapper;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteMapper;
import com.github.beatrizgomees.api.rheumaPlanner.service.noteService.NoteServiceImpl;
import com.github.beatrizgomees.api.rheumaPlanner.domain.note.NoteRequest;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Path("/notes" )
@Tag(name = "notes", description = "teste")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NoteController {

    @Inject
    NoteServiceImpl noteServiceImpl;


    @POST
    @Operation(summary = "register new notes in the database")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNote(NoteRequest noteRequest){
        NoteMapper mapper = new NoteMapper();
        NoteDTO noteDTO = mapper.convertRequestToDTO(noteRequest);
        noteServiceImpl.create(noteDTO);
        return Response.ok(noteRequest).status(201).build();
    }

    @GET
    @Operation(summary = "Search all banknotes registered in the bank")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(){
        List<Document> documentList = noteServiceImpl.getAll();
        return Response.ok(documentList).status(200).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Search for notes registered at the bank by ID")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findNoteById(@PathParam("id") UUID id) {
        Optional<Document> noteRequest =  noteServiceImpl.findById(id);
        return Response.ok(noteRequest).status(200).build();

    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "delete notes by id")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOneNote(@PathParam("id") UUID id){
        noteServiceImpl.delete(id);
        return Response.ok().status(200).build();
    }


    @PUT
    @Path("/{id}")
    @Operation(summary = "updates notes already registered in the bank")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, NoteRequest noteRequest) {
        NoteMapper mapper = new NoteMapper();
        NoteDTO noteDTO = mapper.convertRequestToDTO(noteRequest);
        Document document = mapper.convertDtoToDocument(noteDTO);
        noteServiceImpl.update(UUID.fromString(id), document);
        return Response.ok(noteRequest).status(200).build();
    }
}
