package dropwizard.java.example.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import static java.lang.String.format;
import static java.lang.Thread.currentThread;

public class DiagnosticContextFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private static final Logger LOG = LoggerFactory.getLogger(DiagnosticContextFilter.class);
    private final String REQUEST_ID_KEY = "id";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String id = UUID.randomUUID().toString();
        LOG.info(format("[%s] PUT : %s", currentThread().getName(), id));
        MDC.put(REQUEST_ID_KEY, id);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        String id = MDC.get(REQUEST_ID_KEY);
        LOG.info(format("[%s] REMOVE : %s", currentThread().getName(), id));
    }
}
