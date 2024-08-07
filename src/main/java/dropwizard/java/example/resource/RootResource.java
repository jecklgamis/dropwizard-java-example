package dropwizard.java.example.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.Map;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/")
@Produces({APPLICATION_JSON})
public class RootResource {
    private final String name;

    public RootResource(String name) {
        this.name = name;
    }

    @GET
    public Response root() {
        var entity = Map.of("name", name, "message", "It works on my machine!");
        return Response.ok().entity(entity).build();
    }

}
