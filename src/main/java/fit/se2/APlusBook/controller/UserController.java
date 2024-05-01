package fit.se2.APlusBook.controller;
import fit.se2.APlusBook.model.User;
import fit.se2.APlusBook.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public User saveOrUpdate(User user) {
        if  (user.getId() < 0) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    @RequestMapping(value = "/admin/account/list")
    public String getAllAccounts(Model model) {
        List<User> accounts = userRepository.findAll();
        model.addAttribute("accounts", accounts);
        return "accountList";
    }

    @RequestMapping(value = "/admin/account/details/{id}")
    public String getAccountById(@PathVariable(value = "id") Long id, Model model) {
        User account = userRepository.getById(id);
        model.addAttribute("account", account);
        return "accountDetail";
    }

    @RequestMapping(value = "/admin/account/search")
    public String searchAccount(@RequestParam(value = "regex") String str, Model model) {
        List<User> accounts = userRepository.findByUserName(str);
        if (!accounts.isEmpty()) {
           accounts = userRepository.findByPhoneNum(str);
           if (!accounts.isEmpty()) {
               accounts = userRepository.findByEmail(str);
           }
        }

        model.addAttribute("accounts", accounts);
        return "searchAccount";
    }

    @RequestMapping(value = "/admin/account/update/{id}")
    public String updateAccount(Model model,
                             @ModelAttribute("account") User account) {
        User updateUser = saveOrUpdate(account);
        model.addAttribute("account", updateUser);
        return "accountUpdate";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/admin/account/delete/{id}")
    public String deleteAccount(@PathVariable(value = "id") Long id) {
        if(userRepository.existsById(id)) {
            User user = userRepository.getById(id);
            entityManager.remove(user);
        }
        return "redirect:/admin/account/list";
    }
}
