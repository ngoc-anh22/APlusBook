package fit.se2.APlusBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.se2.APlusBook.model.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, Long> {

    Optional<Book> findBook(String string);

    
}
