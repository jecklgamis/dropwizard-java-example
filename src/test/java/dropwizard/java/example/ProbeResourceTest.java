package dropwizard.java.example;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(DropwizardExtensionsSupport.class)
public class ProbeResourceTest {

    private static final DropwizardAppExtension<AppConfig> EXT = new DropwizardAppExtension<>(App.class,
            ResourceHelpers.resourceFilePath("config.yaml"), ConfigOverride.randomPorts());

    @Test
    public void testLivenessProbe() {
        Response response = client().target(String.format("http://127.0.0.1:%d/probe/live",
                EXT.getLocalPort())).request().get(Response.class);
        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));
        Map entity = response.readEntity(Map.class);
        assertEquals("I am alive!", entity.get("message"));
    }

    @Test
    public void testReadinessProbes() {
        Response response = client().target(String.format("http://127.0.0.1:%d/probe/ready",
                EXT.getLocalPort())).request().get(Response.class);
        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));
        Map entity = response.readEntity(Map.class);
        assertEquals("I am ready!", entity.get("message"));
    }

    private Client client() {
        ClientConfig config = new ClientConfig();
        config.property(ClientProperties.CONNECT_TIMEOUT, 5000);
        config.property(ClientProperties.READ_TIMEOUT, 15000);
        return JerseyClientBuilder.createClient(config);
    }
}


