package gr.hua.ds.postponement.entity;

import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Table(name = "authorities")
@IdClass(AuthPK.class)
public class Authorities implements GrantedAuthority {
//public class Authorities {

//    Tsadimas original
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "username")
//    private User user;

    @Id
    @JsonBackReference
    //@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY )
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Id
    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

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
