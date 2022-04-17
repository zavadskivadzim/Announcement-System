package com.zavadski.security;

import com.zavadski.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static UserDetailsImpl fromUserToUserDetails(User user) {

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.login = user.getLogin();
        userDetails.password = user.getPassword();
        //todo
//        userDetails.grantedAuthorities = Collections.singletonList(
//                new SimpleGrantedAuthority(user.getRoles().get()));

        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
}
