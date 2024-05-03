package fit.se2.APlusBook.repository;

import java.util.List;

import fit.se2.APlusBook.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fit.se2.APlusBook.model.Book;


public interface BookRepository extends JpaRepository <Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByCategory(Category category);
    @Query("SELECT b FROM Book b WHERE b.price = :price AND b.publisher = :publisher AND b.category = :category")
    List<Book> findByFilters(@Param("price") double price, @Param("publisher") String publisher, @Param("category") String category);

}
