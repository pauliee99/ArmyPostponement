package gr.hua.dit.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RoleServicesEntityPK implements Serializable {
    private int roleId;
    private int serviceId;

    @Column(name = "role_id")
    @Id
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name = "service_id")
    @Id
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

        RoleServicesEntityPK that = (RoleServicesEntityPK) o;

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
