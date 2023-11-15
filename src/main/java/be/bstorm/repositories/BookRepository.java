package be.bstorm.repositories;

import be.bstorm.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE :title")
    Optional<Book> findBookByTitle(String title);

    @Query("SELECT count(b) > 0 FROM Book b WHERE b.title LIKE :title")
    boolean existsByTitle(String title);
}
