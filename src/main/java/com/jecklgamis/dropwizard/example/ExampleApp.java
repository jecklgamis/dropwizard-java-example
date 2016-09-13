package com.jecklgamis.dropwizard.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jecklgamis.dropwizard.example.filter.DiagnosticContextFilter;
import com.jecklgamis.dropwizard.example.healthcheck.DefaultHealthCheck;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.Path;

import static com.jecklgamis.dropwizard.example.InjectorHelper.instancesWithAnnotation;

public class ExampleApp extends io.dropwizard.Application<ExampleAppConfig> {
    private Injector injector;

    @Override
    public void run(ExampleAppConfig config, Environment env) throws Exception {
        injector = Guice.createInjector(new ExampleAppModule());
        instancesWithAnnotation(injector, Path.class).forEach((r) -> env.jersey().register(r));
        env.healthChecks().register("default", new DefaultHealthCheck());
        env.jersey().register(new LoggingFilter());
        env.jersey().register(new DiagnosticContextFilter());
    }

    public static void main(String args[]) throws Exception {
        new ExampleApp().run(args);
    }
}
