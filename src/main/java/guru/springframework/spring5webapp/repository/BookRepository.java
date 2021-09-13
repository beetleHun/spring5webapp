package guru.springframework.spring5webapp.repository;

import guru.springframework.spring5webapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
