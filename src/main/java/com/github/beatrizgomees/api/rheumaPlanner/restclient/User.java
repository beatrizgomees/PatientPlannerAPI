package com.github.beatrizgomees.api.rheumaPlanner.restclient;


import java.util.UUID;


public class User {


    private UUID id;

    private String name;

    private String username;


    private String email;

    private String password;

    public User() {

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String email, String username, String passwordHash) {
        this();
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = passwordHash;
    }


}