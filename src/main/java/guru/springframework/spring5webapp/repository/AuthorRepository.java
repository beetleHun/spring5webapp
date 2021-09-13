package guru.springframework.spring5webapp.repository;

import guru.springframework.spring5webapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
