package gr.hua.ds.postponement.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "office")
public class Office {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Basic
    @Column(name = "phone", nullable = true, length = 14)
    private String phone;

    @Basic
    @Column(name = "enabled", nullable = false)
    private int enabled;

//  !!!!!!!!!!!!!!!!!   S O S   !!!!!!!!!!!
//  Αν ζητήσεις 1 postponement, φέρνει  KAI όλα τα postponements του office
//
//    @OneToMany(mappedBy = "office",
//            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
//            fetch = FetchType.LAZY )
//    private List<Postponement> postponements;   // or  private Collection<Postponement> postponements;???????
//
//    public List<Postponement> getPostponements() {
//        return postponements;
//    }
//
//    public void setPostponements(List<Postponement> postponements) {
//        this.postponements = postponements;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Office office = (Office) o;

        if (id != office.id) return false;
        if (enabled != office.enabled) return false;
        if (description != null ? !description.equals(office.description) : office.description != null) return false;
        if (phone != null ? !phone.equals(office.phone) : office.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + enabled;
        return result;
    }

 }
