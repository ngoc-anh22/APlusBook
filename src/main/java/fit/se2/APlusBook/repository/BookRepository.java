package fit.se2.APlusBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import fit.se2.APlusBook.model.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByCategory(String category);
    List<Book> findByFilters(@Param("price") double price, @Param("publisher") String publisher, @Param("category") String category);

    Optional<Book> findBook(String string);

    
}