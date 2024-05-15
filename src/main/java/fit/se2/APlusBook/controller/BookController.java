
package fit.se2.APlusBook.controller;

import java.util.ArrayList;
import java.util.List;

import fit.se2.APlusBook.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import fit.se2.APlusBook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import fit.se2.APlusBook.model.Book;
import fit.se2.APlusBook.repository.BookRepository;


@Controller
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;

    // Tìm sách theo categoryId
    @RequestMapping(value = "/book/list/{category_id}")
    public String getAllBookbyCategoryId(@PathVariable(value = "id") Long category_id, Model model) {
        List<Book> books = bookRepository.findByCategoryId(category_id);
        model.addAttribute("books", books);
        return "book/bookListByCategory";
    }

    // Lấy sách để show ở homepage
    @GetMapping(value="/book/homepage")
    public String getHomePage(Model model) {
        List<Book> languageBooks = bookRepository.getTop5BooksByCategoryId(4);
        model.addAttribute("languageBooks", languageBooks);

        List<Book> novelBooks = bookRepository.getTop5BooksByCategoryId(3);
        model.addAttribute("novelBooks", novelBooks);

        List<Book> literatureBooks = bookRepository.getTop5BooksByCategoryId(1);
        model.addAttribute("literatureBooks", literatureBooks);

        List<Book> livingSkillBooks = bookRepository.getTop5BooksByCategoryId(14);
        model.addAttribute("livingSkillBooks", livingSkillBooks);

        return "book/homePage";
    }
    // Lấy danh sách tất cả các sách
    @GetMapping(value="/book/list")
    public String getAllBook(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0")int from,
            @RequestParam(defaultValue = "0")int to)
    {
        Page<Book> bookPage;
        Pageable pageable = PageRequest.of(page, size);
        if (from > 0 && to > 0) {
           bookPage = bookRepository.findByPriceBetween(from, to, pageable);
        }
         else if (title != null && !title.isEmpty()) {
            // If title is provided, search by title
            bookPage = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
            model.addAttribute("title", title);
        } else {
            // Otherwise, retrieve all books
            bookPage = bookRepository.findAll(pageable);
        }
        List<Book> books = bookPage.getContent();
        List<List<Book>> rows = new ArrayList<>();
        for (int i = 0; i < books.size(); i += 5) {
            rows.add(books.subList(i, Math.min(i + 5, books.size())));
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("rows", rows);
        return "book/bookList"; // This should be the name of your Thymeleaf template file
    }

    // Lấy chi tiết một cuốn sách
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/book/{id}")
    public String getBookById(@PathVariable(value = "id") Long id, Model model) {
        Book book = bookRepository.getById(id);
        model.addAttribute("book", book);
        return "book/bookDetail";
    }
    // Update thông tin sách
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/book/update/{id}")
    public String updateBook(@PathVariable(value = "id") Long id, Model model) {
        Book book = bookRepository.getById(id);
        model.addAttribute("book", book);
        return "bookUpdate";
    }

    // Xóa sách
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "book/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Long id) {
        if(bookRepository.existsById(id)) {
            Book book = bookRepository.getById(id);
            bookRepository.delete(book);
        }
        return "redirect:/book/list";
    }

    // Lưu sách
    @RequestMapping(value = "/book/save")
    public String saveBook(Book book, BindingResult result) {
        bookRepository.save(book);
        return "redirect:/book/detail/" + book.getId();
    }

    // Thêm sách
    @RequestMapping(value = "/book/add")
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "bookAdd";
    }

    // Chèn sách vào list
    @RequestMapping(value = "/book/insert")
    public String insertBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/book/detail";
    }
    // Add to cart
    @GetMapping("/my-cart")
    public String showCart() {
        return "myCart";
    }

    // Show categories
    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Add comment review

}
