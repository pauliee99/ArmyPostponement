package gr.hua.dit.entity;

import javax.persistence.*;

@Entity
@Table(name = "RoleServices", schema = "army", catalog = "")
@IdClass(RoleServicesEntityPK.class)
public class RoleServicesEntity {
    private int roleId;
    private int serviceId;

    @Id
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "service_id")
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleServicesEntity that = (RoleServicesEntity) o;

        if (roleId != that.roleId) return false;
        if (serviceId != that.serviceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + serviceId;
        return result;
    }
}
