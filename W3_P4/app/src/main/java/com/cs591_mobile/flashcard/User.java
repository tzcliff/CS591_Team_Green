package com.cs591_mobile.flashcard;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public boolean isValid(String username, String password) {
        return this.password.equals(password) && this.username.equals(username);
    }

    public String getUsername() {
        return username;
    }

}
