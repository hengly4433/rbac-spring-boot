package com.blockcode.rbac.dto;

import java.util.Date;
import java.util.Set;

public class AuthResponse {
    private String token;
    private String username;
    private Set<String> roles;
    private Date expiration;

    public AuthResponse() {
    }

    public AuthResponse(String token, String username, Set<String> roles, Date expiration) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
