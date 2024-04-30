package fit.se2.APlusBook.controller;

import fit.se2.APlusBook.model.User;
import fit.se2.APlusBook.dto.UserDto;
import fit.se2.APlusBook.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String registerHandle(Model model, @Valid UserDto ut, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("user", ut);
            return "register";
        } else {
            userRepository.save(new User(ut, new BCryptPasswordEncoder(4)));
            model.addAttribute("user", new UserDto());
            model.addAttribute("success", true);
            return "register";
        }
    }
}
