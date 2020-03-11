package dev.yavuztas.boilerplate.springbootwebapp.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class User extends AuthenticatedUser {

    @Id
    @Column(name = "iduser")
    private Long id;

    private String username;
    private String password;

    @JoinColumn(name = "idrole")
    @ManyToOne
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean hasRole(String type){
        return RoleType.getRole(this.role).equals(type);
    }

    public boolean isAdmin(){
        return hasRole(RoleType.ADMIN);
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(RoleType.getRole(this.role));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("role=" + role)
                .toString();
    }
}
