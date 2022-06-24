package com.codegym.secirity.userprinciple;

import com.codegym.model.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;



public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;


    private String email;

    private String avatar;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private String passwordConfirm;

    public UserPrinciple(Long id, String name,
                         String email,String passwordConfirm, String password, String avatar
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordConfirm = passwordConfirm;
        this.password = password;
        this.avatar = avatar;

    }

    public static UserPrinciple build(AppUser user) {

        return new UserPrinciple(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPassword(),
                user.getAvatar()

        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
