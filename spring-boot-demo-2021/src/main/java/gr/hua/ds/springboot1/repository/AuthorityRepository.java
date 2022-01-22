package gr.hua.ds.springboot1.repository;

import gr.hua.ds.springboot1.entity.AuthPK;
import gr.hua.ds.springboot1.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository  extends JpaRepository<Authorities, AuthPK> {
}
