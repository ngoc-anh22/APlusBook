package fit.se2.APlusBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController{
    @RequestMapping("/")
    public String index() {
        return "redirect:/book/homepage";
    }
}
