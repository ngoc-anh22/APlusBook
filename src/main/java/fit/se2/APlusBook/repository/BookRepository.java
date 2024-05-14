package fit.se2.APlusBook.repository;

import java.util.List;

import fit.se2.APlusBook.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fit.se2.APlusBook.model.Book;


public interface BookRepository extends JpaRepository <Book, Long> {
    Page<Book> findByCategory(Category category, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.price = :price AND b.avg_rate = :avg_rate AND b.category = :category")
    List<Book> findByFilters(@Param("price") double price, @Param("avg_rate") int avg_rate, @Param("category") String category);

    List<Book> findByCategoryId(Long category_id);

    List<Book> getTop5BooksByCategoryId(int i);

    List<Book> findByTitleContainingIgnoreCase(String title);

    static void saveBook() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveBook'");
    }

    static Book getBookById(Long bookId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookById'");
    }

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}