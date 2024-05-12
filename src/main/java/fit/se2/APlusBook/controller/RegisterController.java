package fit.se2.APlusBook.controller;

import fit.se2.APlusBook.dto.UserDto;
import fit.se2.APlusBook.model.Category;
import fit.se2.APlusBook.model.User;
import fit.se2.APlusBook.model.UserSimple;
import fit.se2.APlusBook.repository.CategoryRepository;
import fit.se2.APlusBook.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/log-in")
    public String login() {
        return "Authentication/login";
    }

    @GetMapping("/register")
    public String register() {
        return "Authentication/register";
    }
    @PostMapping("/register-process")
    public String registerHandle(Model model, @Valid UserSimple ut, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("user", ut);
            return "Authentication/register";
        } else {
            userRepository.save(new User(ut, new BCryptPasswordEncoder(4)));
            model.addAttribute("user", new UserSimple());
            model.addAttribute("success", true);
            return "Authentication/register";
        }
    }
    @RequestMapping(value = {"/log-in"})
    public String logIn(final Model model,
                        final HttpServletRequest request,
                        final HttpServletResponse response)
            throws IOException {

        return "Authentication/login";
    }
}
