package fit.se2.APlusBook.controller;

import java.util.List;

import fit.se2.APlusBook.model.Category;
import fit.se2.APlusBook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fit.se2.APlusBook.model.Notification;
import fit.se2.APlusBook.repository.NotificationRepository;

@Controller
public class NotificationController {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    @RequestMapping(value = "/notification/list")
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/notification/detail/{id}") 
    public String getNotificationById(@PathVariable(value = "id") Long id, Model model) {
        Notification notification = notificationRepository.getById(id);
        model.addAttribute("notification", notification);
        return "notificationDetail";
    }

    @RequestMapping(value = "/notification/add")
    public String addNotification(Model model) {
        Notification notification = new Notification();
        model.addAttribute("notification", notification);
        return "notificationAdd";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/notification/update/{id}")
    public String updateNotification(@PathVariable(value = "id") Long id, Model model) {
        Notification notification = notificationRepository.getById(id);
        model.addAttribute("notification", notification);
        return "notificationUpdate";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/notification/delete/{id}")
    public String deleteNotification(@PathVariable(value = "id") Long id) {
        if(notificationRepository.existsById(id)) {
            Notification notification = notificationRepository.getById(id);
            notificationRepository.delete(notification);
        }
        return "notificationDelete";
    }

    @RequestMapping(value = "/notification/save") 
    public String saveNotification(Notification notification, BindingResult result) {
        notificationRepository.save(notification);
        return "redirect:/notification/detail/" + notification.getId();
    }

    @RequestMapping(value = "/notification/insert")
    public String insertNotification(Notification notification) {
        notificationRepository.save(notification);
        return "redirect:/notification/detail/";
    }
    
}
