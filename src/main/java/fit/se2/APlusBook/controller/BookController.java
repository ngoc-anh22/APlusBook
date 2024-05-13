
package fit.se2.APlusBook.controller;

import java.util.ArrayList;
import java.util.List;

import fit.se2.APlusBook.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import fit.se2.APlusBook.repository.CategoryRepository;
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

@Controller
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CategoryRepository categoryRepository;


    @RequestMapping(value = "/book/list/{category_id}")
    public String getAllBookbyCategoryId(@PathVariable(value = "id") Long category_id, Model model) {
        List<Book> books = bookRepository.findByCategoryId(category_id);
        model.addAttribute("books", books);
        return "book/bookListByCategory";
    }

    // Lấy sách để show ở homepage
    @GetMapping(value="/")
    public String getHomePage(Model model) {
        List<Book> comicBooks = bookRepository.getTop5BooksByCategoryId(8);
        model.addAttribute("comicBooks", comicBooks);

        List<Book> novelBooks = bookRepository.getTop5BooksByCategoryId(3);
        model.addAttribute("novelBooks", novelBooks);

        List<Book> literatureBooks = bookRepository.getTop5BooksByCategoryId(1);
        model.addAttribute("literatureBooks", literatureBooks);

        List<Book> livingSkillBooks = bookRepository.getTop5BooksByCategoryId(14);
        model.addAttribute("livingSkillBooks", livingSkillBooks);

        return "book/homePage";
    }
    @GetMapping(value="/book/list")
    public String getAllBook(Model model, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "30")int size) {
        // Example list of book titles
        Page<Book> bookPage = bookRepository.findAll(PageRequest.of(page, size));
        // Convert the list of books into rows with 6 books per row
        List<Book> books = bookPage.getContent();
        List<List<Book>> rows = new ArrayList<>();
        for (int i = 0; i < books.size(); i += 6) {
            rows.add(books.subList(i, Math.min(i + 6, books.size())));
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("rows", rows);
        return "book/bookList"; // This should be the name of your Thymeleaf template file
    }
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/book/{id}")
    public String getBookById(@PathVariable(value = "id") Long id, Model model) {
        Book book = bookRepository.getById(id);
        model.addAttribute("book", book);
        return "book/bookDetail";
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
    @RequestMapping(value = "/book/delete/{id}")
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
    public String insertBook(@ModelAttribute Book book) {
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
    @GetMapping("/my-cart")
    public String showCart() {
        return "myCart";
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
