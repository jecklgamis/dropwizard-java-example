package com.jecklgamis.dropwizard.example.resource;

import com.google.common.collect.ImmutableMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/")
@Produces({APPLICATION_JSON})
public class RootResource {

    @GET
    public Response list() {
        Map entity = ImmutableMap.builder().put("message", "It works!").build();
        return Response.ok().entity(entity).build();
    }

}
