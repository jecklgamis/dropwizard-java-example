package com.jecklgamis.dropwizard.example;

import com.google.inject.AbstractModule;
import com.jecklgamis.dropwizard.example.resource.RootResource;
import com.jecklgamis.dropwizard.example.resource.UserResource;

public class ExampleAppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RootResource.class);
        bind(UserResource.class);
    }
}

