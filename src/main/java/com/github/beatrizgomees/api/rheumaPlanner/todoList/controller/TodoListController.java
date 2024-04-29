package com.github.beatrizgomees.api.rheumaPlanner.todoList.controller;


import com.github.beatrizgomees.api.rheumaPlanner.core.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.todoList.dto.TodoListRequest;
import com.github.beatrizgomees.api.rheumaPlanner.todoList.service.TodoListServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/todoList")
@Tag(name = "todoList")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoListController {

    @Inject
    TodoListServiceImpl todoListService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNote(TodoListRequest todoListRequest){
        todoListService.create(todoListRequest);
        return Response.ok(todoListRequest).status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(){
        List<Document> documentList = todoListService.getAll();
        return Response.ok(documentList).status(200).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findNoteById(@PathParam("id") String id)  throws FindByIdException {
        Document document;
        document = todoListService.findById(id);
        return Response.ok(document).status(200).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOneNote(@PathParam("id") String id){
        todoListService.delete(id);
        return Response.ok().status(200).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, Document document) throws FindByIdException {
        todoListService.update(id, document);
        return Response.ok(document).status(200).build();
    }
}
