package com.apps.managetournament;

public class User {

    private String name;
    private String userName;
    private String id;


    public User(String name, String userName, String id) {
        this.name = name;
        this.userName = userName;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }
}
