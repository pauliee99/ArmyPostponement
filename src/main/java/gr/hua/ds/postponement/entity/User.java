package gr.hua.ds.postponement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Basic
    @Column(name = "enabled", nullable = false)
    //private boolean enabled;
    private int enabled;

    @Basic
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Basic
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Basic
    @Column(name = "asm", nullable = true)
    private Integer asm;

    @Basic
    @Column(name = "office", nullable = false)
    private int office;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Collection<Authorities> authorities = new HashSet<>();
    //private Collection<Authorities> authorities;

//    @ManyToOne
//    @JoinColumn(name = "office", referencedColumnName = "id", nullable = false)
//    private Office officeByOffice;

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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAsm() {
        return asm;
    }

    public void setAsm(Integer asm) {
        this.asm = asm;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        if (enabled != user.enabled) return false;
//        if (office != user.office) return false;
//        if (username != null ? !username.equals(user.username) : user.username != null) return false;
//        if (password != null ? !password.equals(user.password) : user.password != null) return false;
//        if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) return false;
//        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
//        if (asm != null ? !asm.equals(user.asm) : user.asm != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = username != null ? username.hashCode() : 0;
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        result = 31 * result + enabled;
//        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
//        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
//        result = 31 * result + (asm != null ? asm.hashCode() : 0);
//        result = 31 * result + office;
//        return result;
//    }

//  public Collection<Authorities> getAuthoritiesByUsername() { return authoritiesByUsername; }
    public Collection<Authorities> getAuthorities() { return authorities; }

//  public void setAuthoritiesByUsername(Collection<Authorities> authoritiesByUsername) { this.authoritiesByUsername = authoritiesByUsername; }
    public void setAuthorities(Collection<Authorities> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authorities authority) {
        this.authorities.add(authority);
    }


//    public Office getOfficeByOffice() {
//        return officeByOffice;
//    }
//    public void setOfficeByOffice(Office officeByOffice) {
//        this.officeByOffice = officeByOffice;
//    }

}
