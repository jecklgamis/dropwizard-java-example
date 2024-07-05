package dropwizard.java.example;

import dropwizard.java.example.filter.DiagnosticContextFilter;
import dropwizard.java.example.healthcheck.DefaultHealthCheck;
import dropwizard.java.example.resource.ProbeResource;
import dropwizard.java.example.resource.RootResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;


public class App extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void run(AppConfig config, Environment env) {
        env.jersey().register(new RootResource(config.getAppName()));
        env.jersey().register(new ProbeResource());
        env.jersey().register(new DiagnosticContextFilter());
        env.healthChecks().register("default", new DefaultHealthCheck());
    }
}
