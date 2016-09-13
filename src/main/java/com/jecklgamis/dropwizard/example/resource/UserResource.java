package com.jecklgamis.dropwizard.example.resource;

import com.jecklgamis.dropwizard.example.api.User;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/user")
@Produces({APPLICATION_JSON})
@Consumes({APPLICATION_JSON})
public class UserResource {
    private Map<String, User> userDb = new ConcurrentHashMap<>();

    @GET
    public Response get(@QueryParam("username") String username) {
        return Response.ok().entity(userDb).build();
    }

    @PUT
    public Response put(@NotNull User user) {
        userDb.put(user.getUsername(), user);
        return Response.ok().build();
    }

    @POST
    public Response post(@NotNull User user) {
        userDb.put(user.getUsername(), user);
        return Response.ok().build();
    }

    @DELETE
    public Response delete(@NotNull @QueryParam("username") String username) {
        userDb.remove(username);
        return Response.ok().build();
    }

}
