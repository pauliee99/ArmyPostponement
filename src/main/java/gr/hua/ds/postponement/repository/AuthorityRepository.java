package gr.hua.ds.postponement.repository;

import gr.hua.ds.postponement.entity.AuthPK;
import gr.hua.ds.postponement.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="authorities")
public interface AuthorityRepository  extends JpaRepository<Authorities, AuthPK> {
}
