package fit.se2.APlusBook.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class LogInController {

    @RequestMapping(value = {"/log-in"})
    public String logIn(final Model model,
                        final HttpServletRequest request,
                        final HttpServletResponse response)
            throws IOException {

        return "/login";
    }

}
