package com.github.beatrizgomees.api.rheumaPlanner.notes.controller;

import com.github.beatrizgomees.api.rheumaPlanner.notes.service.NoteServiceImpl;
import com.github.beatrizgomees.api.rheumaPlanner.notes.dto.NoteRequest;
import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.FindByIdException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;


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
        noteServiceImpl.create(noteRequest);
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
    public Response findNoteById(@PathParam("id") String id)  throws FindByIdException {
        Document document;
        document = noteServiceImpl.findById(id);
        return Response.ok(document).status(200).build();

    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "delete notes by id")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOneNote(@PathParam("id") String id){
        noteServiceImpl.delete(id);
        return Response.ok().status(200).build();
    }


    @PUT
    @Path("/{id}")
    @Operation(summary = "updates notes already registered in the bank")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, Document document) throws FindByIdException {
        noteServiceImpl.update(id, document);
        return Response.ok(document).status(200).build();
    }
}
