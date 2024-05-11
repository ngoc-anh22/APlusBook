package fit.se2.APlusBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fit.se2.APlusBook.model.Category;
import fit.se2.APlusBook.repository.CategoryRepository;

@Controller
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
//    @GetMapping("/")
//    public String home(Model model) {
//        List<Category> categories = categoryRepository.findAll();
//        model.addAttribute("categories", categories);
//        return "_layout";
//    }
//    @RequestMapping(value = "/category/list")
    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @SuppressWarnings("deprecation")
    @GetMapping("/category/{id}")
    public String getCategoryById(@PathVariable Long id, Model model) {
        Category category = categoryRepository.getById(id);
        model.addAttribute("category", category);
        return "categoryDetail";
    }

    @RequestMapping(value = "/category/{name}")
    public String getCategoryByName(@PathVariable String name, Model model) {
        Category category = categoryRepository.findByName(name);
        model.addAttribute("categoryName", category);
        return "categoryName";
    }
}
