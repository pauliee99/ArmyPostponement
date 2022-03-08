package gr.hua.ds.postponement.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "reason")
public class Reason {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Basic
    @Column(name = "enabled", nullable = false)
    private int enabled;

//    @OneToMany(mappedBy = "reasonByReason")
//    private Collection<Postponement> postponementsById;

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

        Reason reason = (Reason) o;

        if (id != reason.id) return false;
        if (enabled != reason.enabled) return false;
        if (description != null ? !description.equals(reason.description) : reason.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + enabled;
        return result;
    }

}
