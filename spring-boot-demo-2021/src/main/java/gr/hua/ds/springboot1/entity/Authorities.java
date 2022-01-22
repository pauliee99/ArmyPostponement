package gr.hua.ds.springboot1.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@IdClass(AuthPK.class)
public class Authorities implements GrantedAuthority {

    @Id
    @Column(name = "authority")
    private String authority;

    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authorities() {
    }

    public Authorities(String authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    @Override
    public String toString() {
        return authority;
    }
}