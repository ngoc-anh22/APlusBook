package fit.se2.APlusBook.controller;

import fit.se2.APlusBook.model.Book;
import fit.se2.APlusBook.model.Category;
import fit.se2.APlusBook.repository.BookRepository;
import fit.se2.APlusBook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fit.se2.APlusBook.model.Comment;
import fit.se2.APlusBook.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;

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

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/comment/update/{id}")
    public String updateComment(@PathVariable(value = "id") Long id, Model model) {
        Comment comment = commentRepository.getById(id);
        model.addAttribute("comment", comment);
        return "commentUpdate";
    }

    @RequestMapping(value = "/book/{bookId}/save-comment", method = RequestMethod.POST)
    public String saveComment(@PathVariable(value = "bookId") Long bookId, Comment comment) {
        // Lưu comment vào cơ sở dữ liệu
        commentRepository.save(comment);
        Optional<Book> optionalBook = bookRepository.findById(comment.getBookId());
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            // Calculate the average rating
            book.calculateAverageRating();

            // Update the book in the database
            bookRepository.save(book);

            // Redirect the user to a success page or the book's detail page (depending on your requirements)
            return "redirect:/book/detail/" + bookId;
        } else {
            // Handle the case where the book is not found
            return "redirect:/error";
        }
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
