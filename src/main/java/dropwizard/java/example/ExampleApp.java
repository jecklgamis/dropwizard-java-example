package dropwizard.java.example;

import dropwizard.java.example.filter.DiagnosticContextFilter;
import dropwizard.java.example.healthcheck.DefaultHealthCheck;
import dropwizard.java.example.resource.RootResource;
import io.dropwizard.core.setup.Environment;

public class ExampleApp extends io.dropwizard.core.Application<ExampleAppConfig> {

    public static void main(String[] args) throws Exception {
        new ExampleApp().run(args);
    }

    @Override
    public void run(ExampleAppConfig config, Environment env) {
        env.jersey().register(new RootResource(config.getAppName()));
        env.jersey().register(new DiagnosticContextFilter());
        env.healthChecks().register("default", new DefaultHealthCheck());
    }
}
