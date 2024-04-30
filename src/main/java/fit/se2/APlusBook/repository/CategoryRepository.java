package fit.se2.APlusBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fit.se2.APlusBook.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

}
