package com.sangat.mudah.facebookautocreatewithoutlook.credentials;

public class EmailCredentials {

    public String getUsername() {
        return username;
    }

    public EmailCredentials setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public EmailCredentials setPassword(String password) {
        this.password = password;
        return this;
    }

    private String username;
    private String password;
}
