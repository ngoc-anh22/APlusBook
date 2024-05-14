package fit.se2.APlusBook.controller;

import fit.se2.APlusBook.dto.UserDto;
import fit.se2.APlusBook.model.Category;
import fit.se2.APlusBook.model.User;
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
public class AuthenticationController {
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
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "Authentication/register";
    }
    @PostMapping("/register-process")
<<<<<<< HEAD:src/main/java/fit/se2/APlusBook/controller/AuthenticationController.java
    public String registerHandle(Model model, @ModelAttribute("user") @Valid UserDto ut, BindingResult result, HttpServletRequest request) {
        String confirmPassword = request.getParameter("confirmPassword");
        if (!(ut.getPassword().equals(confirmPassword))) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }
=======
<<<<<<< Updated upstream:src/main/java/fit/se2/APlusBook/controller/RegisterController.java
    public String registerHandle(Model model, @Valid UserSimple ut, BindingResult result) {
=======
    public String registerHandle(Model model, @ModelAttribute("user") @Valid UserDto ut, BindingResult result) {
>>>>>>> Stashed changes:src/main/java/fit/se2/APlusBook/controller/AuthenticationController.java
>>>>>>> 89e3ff1 (Add thymeleaf validation in register.html):src/main/java/fit/se2/APlusBook/controller/RegisterController.java
        if (result.hasErrors()) {
            model.addAttribute("user", ut);
            model.addAttribute("success", false);
            return "Authentication/register";
        } else {
            ut.setRole("USER");
            userRepository.save(new User(ut, new BCryptPasswordEncoder(4)));
<<<<<<< HEAD:src/main/java/fit/se2/APlusBook/controller/AuthenticationController.java
            model.addAttribute("user", new UserDto());
            model.addAttribute("success", true);
            return "Authentication/register";
//            return "redirect:/log-in";
=======
<<<<<<< Updated upstream:src/main/java/fit/se2/APlusBook/controller/RegisterController.java
            model.addAttribute("user", new UserSimple());
            model.addAttribute("success", true);
            return "Authentication/register";
=======
//            model.addAttribute("user", new UserDto());
//            model.addAttribute("success", true);
            return "redirect:/log-in";
>>>>>>> Stashed changes:src/main/java/fit/se2/APlusBook/controller/AuthenticationController.java
>>>>>>> 89e3ff1 (Add thymeleaf validation in register.html):src/main/java/fit/se2/APlusBook/controller/RegisterController.java
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
