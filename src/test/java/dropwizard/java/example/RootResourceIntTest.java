package dropwizard.java.example;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.junit.DropwizardAppRule;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;


public class RootResourceIntTest {
    @ClassRule
    public static final DropwizardAppRule<ExampleAppConfig> RULE =
            new DropwizardAppRule(ExampleApp.class, resourceFilePath("config.yml"),
                    ConfigOverride.config("server.applicationConnectors[1].keyStorePath", "src/main/resources/keystore.pfx"));

    @Test
    public void testDefaultResource() {
        Response response = client().target(format("http://127.0.0.1:%d/", RULE.getLocalPort())).request().get(Response.class);
        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));
        Map entity = response.readEntity(Map.class);
        assertEquals("dropwizard-java-example", entity.get("name"));
    }

    private Client client() {
        ClientConfig config = new ClientConfig();
        config.property(ClientProperties.CONNECT_TIMEOUT, 5000);
        config.property(ClientProperties.READ_TIMEOUT, 15000);
        return JerseyClientBuilder.createClient(config);
    }
}


