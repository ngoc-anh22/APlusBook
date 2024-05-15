package fit.se2.APlusBook.repository;

import java.util.List;

import fit.se2.APlusBook.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import fit.se2.APlusBook.model.Book;


public interface BookRepository extends JpaRepository <Book, Long> {
    Page<Book> findByCategory(Category category, Pageable pageable);
    List<Book> findByCategoryId(Long category_id);
    List<Book> getTop5BooksByCategoryId(int i);
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByPriceBetween(int i, int j, Pageable pageable);
}
