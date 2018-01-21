package com.wathis.listecourse.models;

/**
 * Created by mathisdelaunay on 18/01/2018.
 */

public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
