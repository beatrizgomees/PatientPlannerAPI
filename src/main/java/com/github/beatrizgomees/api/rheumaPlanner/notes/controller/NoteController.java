package com.github.beatrizgomees.api.rheumaPlanner.notes.controller;

import com.github.beatrizgomees.api.rheumaPlanner.notes.service.NoteServiceImpl;
import com.github.beatrizgomees.api.rheumaPlanner.notes.dto.NoteRequest;
import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.FindByIdException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;


@Path("/notes")
@Tag(name = "notes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NoteController {

    @Inject
    NoteServiceImpl noteServiceImpl;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNote(NoteRequest notesDTO){
        NoteRequest noteRequest = noteServiceImpl.create(notesDTO);
        return Response.ok(noteRequest).status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(){
        List<Document> documentList = noteServiceImpl.getAll();
        return Response.ok(documentList).status(200).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findNoteById(@PathParam("id") String id)  throws FindByIdException {
        Document document;
        try {
          document = noteServiceImpl.findById(id);
        } catch (FindByIdException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Note not found").build();
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
        noteServiceImpl.delete(id);
        return Response.ok().status(200).build();
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, Document document) throws FindByIdException {
        noteServiceImpl.update(id, document);
        return Response.ok(document).status(200).build();
    }
}
