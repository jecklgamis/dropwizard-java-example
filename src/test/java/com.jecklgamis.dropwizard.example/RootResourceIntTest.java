package com.jecklgamis.dropwizard.example;

import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.Map;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;


public class RootResourceIntTest {
    @ClassRule
    public static final DropwizardAppRule<ExampleAppConfig> RULE = new DropwizardAppRule(ExampleApp.class, resourceFilePath("config.yml"));

    @Test
    public void testDefaultResource() {
        Response response = client().target(format("http://127.0.0.1:%d/", RULE.getLocalPort())).request().get(Response.class);
        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));
        Map<String, String> entity = response.readEntity(Map.class);
        assertEquals("It works!", entity.get("message"));
    }

    private Client client() {
        ClientConfig config = new ClientConfig();
        config.property(ClientProperties.CONNECT_TIMEOUT, 5000);
        config.property(ClientProperties.READ_TIMEOUT, 15000);
        return JerseyClientBuilder.createClient(config);
    }
}


