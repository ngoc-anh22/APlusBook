package fit.se2.APlusBook.controller;
import fit.se2.APlusBook.model.User;
import fit.se2.APlusBook.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
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

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/account/details")
    public String getAccountById() {
//        @PathVariable(value = "id") Long id, Model model
//        User account = userRepository.getById(id);
//        model.addAttribute("account", account);
        return "Account/accountDetail";
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

    @RequestMapping(value = "/account/update")
    public String updateAccount() {
//        Model model, @ModelAttribute("account") User account
//        User updateUser = saveOrUpdate(account);
//        model.addAttribute("account", updateUser);
        return "Account/accountUpdate";
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
    @GetMapping("/account/change-pasword")
    public String changePassword() {
        return "Account/changePassword";
    }
    @GetMapping("/account/my-order")
    public String myOrder() {
        return "Account/myOrder";
    }
    @GetMapping("/account/manage-products")
    public String manageProducts() {
        return "Account/manageProducts";
    }
    @GetMapping("/account/manage-orders")
    public String manageOrder() {
        return "Account/manageOrders";
    }
    @GetMapping("/account/manage-customers")
    public String manageCustomers() {
        return "Account/manageCustomers";
    }

 }
