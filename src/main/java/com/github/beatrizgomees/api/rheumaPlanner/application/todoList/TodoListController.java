package com.github.beatrizgomees.api.rheumaPlanner.application.todoList;


import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListDTO;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListMapper;
import com.github.beatrizgomees.api.rheumaPlanner.infrastructure.exceptions.FindByIdException;
import com.github.beatrizgomees.api.rheumaPlanner.domain.todoList.TodoListRequest;
import com.github.beatrizgomees.api.rheumaPlanner.service.todoListService.TodoListServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        TodoListMapper mapper = new TodoListMapper();
        TodoListDTO todoListDTO = mapper.convertRequestToDTO(todoListRequest);
        todoListService.create(todoListDTO);
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
    public Response findNoteById(@PathParam("id") UUID id)  {
        Optional<Document> todoListRequest = todoListService.findById(id);
        return Response.ok(todoListRequest).status(200).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOneNote(@PathParam("id") UUID id){
        todoListService.delete(id);
        return Response.ok().status(200).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, TodoListRequest todoListRequest) {
        TodoListMapper mapper = new TodoListMapper();
        TodoListDTO todoListDTO = mapper.convertRequestToDTO(todoListRequest);
        Document document = mapper.convertDtoToDocument(todoListDTO);
        todoListService.update(UUID.fromString(id), document);
        return Response.ok(todoListRequest).status(200).build();
    }
}
