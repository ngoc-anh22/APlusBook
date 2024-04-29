package fit.se2.APlusBook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fit.se2.APlusBook.model.Category;
import fit.se2.APlusBook.repository.CategoryRepository;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    @RequestMapping(value = "/category")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/category/{name}")
    public String getCategoryByName(@PathVariable String name, Model model) {
        Category category = categoryRepository.findByName(name);
        model.addAttribute("categoryName", category);
        return "CategoryName";
    }
}
