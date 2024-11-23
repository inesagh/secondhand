package com.example.secondhandclothes.infrastructure.security;

import com.example.secondhandclothes.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDAODetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthorityList;

    public UserDAODetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.grantedAuthorityList = List.of(user.getRoles().split(","))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthorityList() {
        return grantedAuthorityList;
    }

    public void setGrantedAuthorityList(List<GrantedAuthority> grantedAuthorityList) {
        this.grantedAuthorityList = grantedAuthorityList;
    }

    @Override public String getUsername() {
        return username;
    }

    @Override public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
