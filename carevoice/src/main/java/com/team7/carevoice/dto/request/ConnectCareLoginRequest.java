package com.team7.carevoice.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Data transfer object for login requests.
 */
public class ConnectCareLoginRequest {

    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}