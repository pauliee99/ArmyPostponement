package gr.hua.ds.postponement.repository;

import gr.hua.ds.postponement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path="users")
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}