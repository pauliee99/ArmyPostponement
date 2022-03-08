package gr.hua.ds.postponement.repository;

import gr.hua.ds.postponement.entity.Reason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="reasons")
public interface ReasonRepository extends JpaRepository<Reason, Integer> {
    Reason findByDescription(String reasonDescr);
}
