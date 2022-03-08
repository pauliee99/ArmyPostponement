package gr.hua.ds.postponement.repository;

import gr.hua.ds.postponement.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="offices")
public interface OfficeRepository extends JpaRepository<Office, Integer> {
}
