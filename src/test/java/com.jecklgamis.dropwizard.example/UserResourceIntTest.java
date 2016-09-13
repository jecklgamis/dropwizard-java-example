package com.jecklgamis.dropwizard.example;

import com.jecklgamis.dropwizard.example.api.User;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;


public class UserResourceIntTest {
    @ClassRule
    public static final DropwizardAppRule<ExampleAppConfig> RULE = new DropwizardAppRule(ExampleApp.class, resourceFilePath("config.yml"));

    @Test
    public void testGet() {
        Response response = client().target(format("http://127.0.0.1:%d/user", RULE.getLocalPort()))
                .queryParam("username", "me")
                .request().get(Response.class);
        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));
        assertEquals("{}", response.readEntity(String.class));
    }

    @Test
    public void testPut() {
        Response response = client().target(format("http://127.0.0.1:%d/user", RULE.getLocalPort()))
                .request().put(Entity.entity(new User("me", "me@example.com"), APPLICATION_JSON), Response.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testPost() {
        Response response = client().target(format("http://127.0.0.1:%d/user", RULE.getLocalPort()))
                .request().post(Entity.entity(new User("me", "me@example.com"), APPLICATION_JSON), Response.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testDelete() {
        Response response = client().target(format("http://127.0.0.1:%d/user", RULE.getLocalPort()))
                .request().put(Entity.entity(new User("me", "me@example.com"), APPLICATION_JSON), Response.class);
        assertEquals(200, response.getStatus());

        response = client().target(format("http://127.0.0.1:%d/user", RULE.getLocalPort())).queryParam("username", "me").request().delete(Response.class);
        assertEquals(200, response.getStatus());
    }

    private Client client() {
        ClientConfig config = new ClientConfig();
        config.property(ClientProperties.CONNECT_TIMEOUT, 5000);
        config.property(ClientProperties.READ_TIMEOUT, 15000);
        return JerseyClientBuilder.createClient(config);
    }
}


