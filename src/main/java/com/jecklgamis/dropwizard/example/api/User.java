package com.jecklgamis.dropwizard.example.api;

import javax.validation.constraints.NotNull;

public class User {
    @NotNull
    private String username;
    @NotNull
    private String email;


    public User() {
        this("me", "me@example.com");
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}


