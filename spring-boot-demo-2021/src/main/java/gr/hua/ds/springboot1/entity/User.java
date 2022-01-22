package gr.hua.ds.springboot1.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Authorities> authorities = new HashSet<>();

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Authorities> getAuthorities() {return authorities;    }

    public void setAuthorities(Collection<Authorities> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authorities authority) {
        this.authorities.add(authority);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}