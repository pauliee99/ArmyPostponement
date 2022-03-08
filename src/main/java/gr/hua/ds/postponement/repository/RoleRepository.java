package gr.hua.ds.postponement.repository;

import gr.hua.ds.postponement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="roles")
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
