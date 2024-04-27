package com.github.beatrizgomees.api.rheumanotes.notes.controller;

import com.github.beatrizgomees.api.rheumanotes.notes.NoteService.NoteServiceImpl;
import com.github.beatrizgomees.api.rheumanotes.notes.dto.NoteRequest;
import com.github.beatrizgomees.api.rheumanotes.notes.exceptions.FindNoteByIdException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;


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
        NoteRequest noteRequest = noteServiceImpl.createNote(notesDTO);
        return Response.ok(noteRequest).status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(){
        List<Document> documentList = noteServiceImpl.getNotes();
        return Response.ok(documentList).status(200).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findNoteById(@PathParam("id") String id)  throws FindNoteByIdException {
        Document document;
        try {
          document = noteServiceImpl.findNoteById(id);
        } catch (FindNoteByIdException e) {
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
        noteServiceImpl.deleteNote(id);
        return Response.ok().status(200).build();
    }

}
