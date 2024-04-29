package com.github.beatrizgomees.api.rheumaPlanner.restclient;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("login")
public class UserResource {
    @Inject
    @RestClient
    UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)

    public User login(@QueryParam("email") String email, @QueryParam("senha") String password) {
        return userService.login(email, password);
    }
}
