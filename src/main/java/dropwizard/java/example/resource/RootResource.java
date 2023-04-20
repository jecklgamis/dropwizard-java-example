package dropwizard.java.example.resource;

import com.google.common.collect.ImmutableMap;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.Map;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/")
@Produces({APPLICATION_JSON})
public class RootResource {
    private String name;

    public RootResource(String name) {
        this.name = name;
    }

    @GET
    public Response list() {
        Map entity = ImmutableMap.builder()
                .put("name", name)
                .put("java.version", System.getProperty("java.version"))
                .put("java.runtime.name", System.getProperty("java.runtime.name"))
                .build();
        return Response.ok().entity(entity).build();
    }

}
