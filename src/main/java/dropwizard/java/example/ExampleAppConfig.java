package dropwizard.java.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ExampleAppConfig extends Configuration {
    @JsonProperty
    @NotEmpty
    private String appName = "appName";

    public String getAppName() {
        return appName;
    }
}
