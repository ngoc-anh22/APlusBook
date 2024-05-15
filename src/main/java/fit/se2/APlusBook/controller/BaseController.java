package fit.se2.APlusBook.controller;

import fit.se2.APlusBook.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {
    @ModelAttribute("userLogined")
    public UserDetails getUserLogined() {
        Object userLogined = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return (UserDetails) userLogined;
   }
}
