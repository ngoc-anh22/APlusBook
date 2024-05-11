package fit.se2.APlusBook.controller;

import fit.se2.APlusBook.model.Category;
import fit.se2.APlusBook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fit.se2.APlusBook.model.Comment;
import fit.se2.APlusBook.repository.CommentRepository;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/comment/detail/{id}")
    public String getCommentById (@PathVariable(value = "id") Long id, Model model) {
        Comment comment = commentRepository.getById(id);
        model.addAttribute("comment", comment);
        return "commentDetail";
    }
    @RequestMapping(value = "/comment/add")
    public String addComment(Model model) {
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        return "commentAdd";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/comment/update/{id}")
    public String updateComment(@PathVariable(value = "id") Long id, Model model) {
        Comment comment = commentRepository.getById(id);
        model.addAttribute("comment", comment);
        return "commentUpdate";
    }

    @RequestMapping(value = "/comment/save")
    public String saveComment(Comment comment, BindingResult result) {
        commentRepository.save(comment);
        return "redirect:/comment/detail/" + comment.getId();
    }

    @RequestMapping(value = "/comment/insert")
    public String insertComment(@ModelAttribute Comment comment) {
        commentRepository.save(comment);
        return "redirect:/comment/detail";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/comment/delete/{id}")
    public String deleteComment(@PathVariable(value = "id") Long id) {
        if(commentRepository.existsById(id)) {
            Comment comment = commentRepository.getById(id);
            commentRepository.delete(comment);
        }
        return "commentDelete";
    }
    
}
