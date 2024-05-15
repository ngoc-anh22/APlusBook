package fit.se2.APlusBook.controller;

import java.util.ArrayList;
import java.util.List;

import fit.se2.APlusBook.model.Book;
import fit.se2.APlusBook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fit.se2.APlusBook.model.Category;
import fit.se2.APlusBook.repository.CategoryRepository;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BookRepository bookRepository;

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @SuppressWarnings("deprecation")
    @GetMapping("/category/{id}")
    public String getCategoryById(@PathVariable Long id, Model model, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size) {

        Category category = categoryRepository.getById(id);
        model.addAttribute("category", category);

        Page<Book> bookPage = bookRepository.findByCategory(category, PageRequest.of(page, size));
        List<Book> books = bookPage.getContent();

        // Convert the list of books into rows with 3 books per row
        List<List<Book>> rows = new ArrayList<>();
        for (int i = 0; i < books.size(); i += 5) {
            rows.add(books.subList(i, Math.min(i + 5, books.size())));
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("rows", rows);

        return "Category/categoryDetail";
    }

    @RequestMapping(value = "/category/{name}")
    public String getCategoryByName(@PathVariable String name, Model model) {
        Category category = categoryRepository.findByName(name);
        model.addAttribute("categoryName", category);
        return "categoryName";
    }
}
