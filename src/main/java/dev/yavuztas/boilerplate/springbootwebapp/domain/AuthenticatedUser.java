package dev.yavuztas.boilerplate.springbootwebapp.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Collection;

/**
 * Base abstract class for Spring Security's {@link UserDetails}
 *
 * @author Yavuz Tas
 */
@MappedSuperclass
public abstract class AuthenticatedUser implements UserDetails {

    @Transient
    private String authority;

    public AuthenticatedUser() {
        this("ROLE_USER");
    }

    public AuthenticatedUser(String authority) {
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(this.authority);
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
