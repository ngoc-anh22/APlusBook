package fit.se2.APlusBook.controller;

import java.util.List;

import fit.se2.APlusBook.model.Category;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import fit.se2.APlusBook.model.Book;
import fit.se2.APlusBook.model.Comment;
import fit.se2.APlusBook.repository.BookRepository;
import fit.se2.APlusBook.repository.CommentRepository;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(value = "/book/list")
    public String getAllBook(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "bookList";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/book/detail/{id}")
    public String getBookById(@PathVariable(value = "id") Long id, Model model) {
        Book book = bookRepository.getById(id);
        model.addAttribute("book", book);
        return "bookDetail";
    }

    @RequestMapping(value = "/book/detail/{title}")
    public String searchBookByTitle(@PathVariable(value = "title") String title, Model model) {
        List<Book> books = bookRepository.findByTitle(title);
        model.addAttribute("books", books);
        return "searchBookByTitle";
    }

    @RequestMapping(value = "/book/detail/{category}")
    public String searchByCategory(@PathVariable(value = "category") Category category, Model model) {
        List<Book> books = bookRepository.findByCategory(category);
        model.addAttribute("books", books);
        return "searchBookByCategory";
    }

    @RequestMapping(value = "/book/search")
    public String searchBooksByFilters(
            @RequestParam(value = "price") double price, 
            @RequestParam(value = "publisher") String publisher, 
            @RequestParam(value = "category") String category, Model model) {
        List<Book> books = bookRepository.findByFilters(price, publisher, category);
        model.addAttribute("books", books);
        return "filterResults";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/book/update/{id}")
    public String updateBook(@PathVariable(value = "id") Long id, Model model) {
        Book book = bookRepository.getById(id);
        model.addAttribute("book", book);
        return "bookUpdate";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "book/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Long id) {
        if(bookRepository.existsById(id)) {
            Book book = bookRepository.getById(id);
            bookRepository.delete(book);
        }
        return "redirect:/book/list";
    }
    
    @RequestMapping(value = "/book/save")
    public String saveBook(Book book, BindingResult result) {
        bookRepository.save(book);
        return "redirect:/book/detail/" + book.getId();
    }

    @RequestMapping(value = "/book/add") 
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "bookAdd";
    }

    @RequestMapping(value = "/book/insert")
    public String insertBook(final Model model,
                             final HttpServletRequest request,
                             final HttpServletResponse response,
                             @ModelAttribute("book") Book book, //spring-form binding
                             @RequestAttribute("productAvatar") MultipartFile bookAvatar) {
        bookRepository.save(book);
        return "redirect:/book/detail";
    }

    @RequestMapping(value = "/{bookid}/comment")
    public ResponseEntity<List<Comment>> getCommentsByBookId (@PathVariable Long id) {
        List<Comment> comments = commentRepository.getCommentsByBookId(id);
        if (comments == null || comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(comments);
    }
}
