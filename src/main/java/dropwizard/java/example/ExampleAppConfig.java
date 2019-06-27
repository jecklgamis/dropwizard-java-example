package dropwizard.java.example;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ExampleAppConfig extends Configuration {
    @JsonProperty
    @NotEmpty
    private String appName = "appName";

    public String getAppName() {
        return appName;
    }
}
