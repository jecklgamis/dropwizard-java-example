package dropwizard.java.example.resource;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

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
