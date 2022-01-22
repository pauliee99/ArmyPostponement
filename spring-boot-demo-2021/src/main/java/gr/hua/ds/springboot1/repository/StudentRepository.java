package gr.hua.ds.springboot1.repository;

import gr.hua.ds.springboot1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="students")
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
