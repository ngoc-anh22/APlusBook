package fit.se2.APlusBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fit.se2.APlusBook.model.Order;
import fit.se2.APlusBook.repository.OrderRepository;

@Controller
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    
    @RequestMapping(value = "/order/list")
    public String getAllOrder(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orderList";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/order/detail/{id}")
    public String getOrderById(@PathVariable(value = "id") Long id, Model model) {
        Order order = orderRepository.getById(id);
        model.addAttribute("order", order);
        return "orderDetail";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/order/update/{id}")
    public String updateOrder(@PathVariable(value = "id") Long id, Model model) {
        Order order = orderRepository.getById(id);
        model.addAttribute("order", order);
        return "orderUpdate";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "order/delete/{id}")
    public String deleteOrder(@PathVariable(value = "id") Long id) {
        if(orderRepository.existsById(id)) {
            Order order = orderRepository.getById(id);
            orderRepository.delete(order);
        }
        return "redirect:/order/list";
    }
    
    @RequestMapping(value = "/order/save")
    public String saveOrder(Order order, BindingResult result) {
        orderRepository.save(order);
        return "redirect:/order/detail/" + order.getId();
    }

    @RequestMapping(value = "/order/add") 
    public String addOrder(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        return "orderAdd";
    }

    @RequestMapping(value = "/order/insert")
    public String insertOrder(@ModelAttribute Order order) {
        orderRepository.save(order);
        return "redirect:/order/detail";
    }
    
}
