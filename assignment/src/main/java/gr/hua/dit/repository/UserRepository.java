package gr.hua.dit.repository;

import gr.hua.dit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path="users")
public interface UserRepository extends JpaRepository<User, Integer> {

}
