package com.github.beatrizgomees.api.rheumaPlanner.restclient;


import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/user")
@RegisterRestClient
public interface UserService {


    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/login")
    public User login(@QueryParam("email") String email, @QueryParam("senha") String senha);

}
