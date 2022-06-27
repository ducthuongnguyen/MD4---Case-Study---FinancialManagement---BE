package com.codegym.dto.response;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    Long id;
    String token;
    private String type = "Bearer";
    private String name;
    private String avatar;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse() {
    }

//    public JwtResponse(String token, String type, String name,String avatar, Collection<? extends GrantedAuthority> roles) {
//        this.token = token;
//        this.type = type;
//        this.name = name;
//        this.avatar = avatar;
//        this.roles = roles;
//    }


    public JwtResponse(Long id, String token, String name, String avatar, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;

        this.name = name;
        this.avatar = avatar;
        this.roles = roles;
    }

    public JwtResponse(String token, String name, String avatar, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.name = name;
        this.avatar = avatar;
        this.roles = authorities;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

