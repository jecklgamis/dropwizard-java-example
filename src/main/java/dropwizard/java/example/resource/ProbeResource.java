package dropwizard.java.example.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.Map;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/probe")
@Produces({APPLICATION_JSON})
public class ProbeResource {

    public ProbeResource() {
    }

    @GET
    @Path("/live")
    public Response live() {
        var entity = Map.of("message", "I am alive!");
        return Response.ok().entity(entity).build();
    }

    @GET
    @Path("/ready")
    public Response ready() {
        var entity = Map.of("message", "I am ready!");
        return Response.ok().entity(entity).build();
    }


}
