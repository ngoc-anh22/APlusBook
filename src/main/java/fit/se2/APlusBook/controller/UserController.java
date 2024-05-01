package fit.se2.APlusBook.controller;

public class UserController {
<<<<<<< Updated upstream
    
=======

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public User saveOrUpdate(User user) {
        if (user.getId() == null || user.getId() <0) {
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
>>>>>>> Stashed changes
}
