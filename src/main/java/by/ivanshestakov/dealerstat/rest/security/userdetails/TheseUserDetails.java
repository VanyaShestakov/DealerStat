package by.ivanshestakov.dealerstat.rest.security.userdetails;

import by.ivanshestakov.dealerstat.rest.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class TheseUserDetails implements UserDetails {

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public TheseUserDetails toTheseUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.grantedAuthorities = Collections
                .singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
        return this;
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
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
