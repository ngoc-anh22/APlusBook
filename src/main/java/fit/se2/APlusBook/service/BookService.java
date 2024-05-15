package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.Book;
import fit.se2.APlusBook.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class BookService {

    private EntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    public Book saveOrUpdate(Book book) {
        if  (book.getId() < 0) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    private boolean isEmptyUploadFile(MultipartFile image) {
        return image != null && !Objects.requireNonNull(image.getOriginalFilename()).isEmpty();
    }

    private String getUniqueUploadFileName(String fileName) {
        String[] splitFileName = fileName.split("\\.");
        return splitFileName[0] + System.currentTimeMillis() + "." + splitFileName[1];
    }

    @Transactional
    public Book saveProduct(Book book, MultipartFile Avatar)
            throws IllegalStateException, IOException {
        if (isEmptyUploadFile(Avatar)) { // có đẩy avatar lên.

            String fileName = getUniqueUploadFileName(Objects.requireNonNull(Avatar.getOriginalFilename()));

            String pathToAvatar = "D:/Workspace/SE2(Tut)/APlusBook/APlusBook/src/main/resources/static/upload" + fileName;

            Avatar.transferTo(new File(pathToAvatar));

            book.setImage("upload" + fileName);
        }
        return saveOrUpdate(book);

    }
    @Transactional
    public Book updateProduct(Book p, MultipartFile Avatar)
            throws IllegalStateException, IOException {

        Book bookInDb = bookRepository.getById(p.getId());

        if (isEmptyUploadFile(Avatar)) {
            new File("D:/Workspace/SE2(Tut)/APlusBook/APlusBook/src/main/resources/static/upload" + bookInDb.getImage()).delete();

            String fileName = getUniqueUploadFileName(Objects.requireNonNull(Avatar.getOriginalFilename()));
            Avatar.transferTo(new File("D:/Workspace/SE2(Tut)/APlusBook/APlusBook/src/main/resources/static/upload" + fileName));
            p.setImage("upload" + fileName);
        } else {
            p.setImage(bookInDb.getImage());
        }
        return saveOrUpdate(p);
    }
}
