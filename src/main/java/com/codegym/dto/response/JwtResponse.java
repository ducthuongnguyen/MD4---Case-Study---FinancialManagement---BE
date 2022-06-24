package com.codegym.dto.response;


public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String name;
    private String email;
    private String avatar;
    private String password;

    public JwtResponse(String accessToken, Long id, String name, String email, String avatar ,String password) {
        this.token = accessToken;
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.password=password;

    }

    public JwtResponse(Long id, String token, String type, String name, String email, String avatar, String password) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.password = password;

    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    }

