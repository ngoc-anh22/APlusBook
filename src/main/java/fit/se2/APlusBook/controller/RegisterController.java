package fit.se2.APlusBook.controller;

import fit.se2.APlusBook.model.User;
import fit.se2.APlusBook.model.UserSimple;
import fit.se2.APlusBook.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/log-in")
    public String login() {
        return "Authentication/login";
    }

    @GetMapping("/register")
    public String register() {
        return "Authentication/register";
    }
    @PostMapping("/register")
    public String registerHandle(Model model, @Valid UserSimple ut, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("user", ut);
            return "register";
        } else {
            userRepository.save(new User(ut, new BCryptPasswordEncoder(4)));
            model.addAttribute("user", new UserSimple());
            model.addAttribute("success", true);
            return "register";
        }
    }
}
