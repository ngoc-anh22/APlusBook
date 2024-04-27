package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.Book;
import org.springframework.stereotype.Service;


@Service
public class BookService extends BaseService<Book>{
    protected Class<Book> clazz() {
        return Book.class;
    }
}
