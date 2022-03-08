package gr.hua.ds.postponement.repository;

import gr.hua.ds.postponement.entity.Office;
import gr.hua.ds.postponement.entity.Postponement;
import gr.hua.ds.postponement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path="postponements")
public interface PostponementRepository extends JpaRepository<Postponement, Integer> {

    @Query("select p from Postponement p where p.userIn = ?1")
    List<Postponement> findAllByUserIn(User userIn);

    List<Postponement> findAllByOffice(Office office);

    //boolean findFirstByUserIn(User userIn);

    @Query("select distinct p from Postponement p where p.userIn = ?1")
    Optional<Postponement> findDistinctByUserIn(User userIn);
}

