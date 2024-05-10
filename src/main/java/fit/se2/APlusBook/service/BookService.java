package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.Book;
import fit.se2.APlusBook.model.BookImages;
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

    @Autowired
    private BookImagesService bookImagesService;

    public Book saveOrUpdate(Book book) {
        if  (book.getId() < 0) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    private boolean isEmptyUploadFile(MultipartFile[] images) {
        if (images == null || images.length == 0)
            return false;

        return images.length != 1 || !Objects.requireNonNull(images[0].getOriginalFilename()).isEmpty();
    }

    private boolean isEmptyUploadFile(MultipartFile image) {
        return image != null && !Objects.requireNonNull(image.getOriginalFilename()).isEmpty();
    }

    private String getUniqueUploadFileName(String fileName) {
        String[] splitFileName = fileName.split("\\.");
        return splitFileName[0] + System.currentTimeMillis() + "." + splitFileName[1];
    }

    @Transactional
    public Book saveProduct(Book book, MultipartFile Avatar, MultipartFile[] productPictures)
            throws IllegalStateException, IOException {
        if (isEmptyUploadFile(Avatar)) { // có đẩy avatar lên.

            String fileName = getUniqueUploadFileName(Objects.requireNonNull(Avatar.getOriginalFilename()));

            String pathToAvatar = "D:/Future/JavaWeb/Upload/Product/avatar/" + fileName;

            Avatar.transferTo(new File(pathToAvatar));

            book.setAvatar("product/avatar/" + fileName);
        }

        if (isEmptyUploadFile(productPictures)) {
            for (MultipartFile pic : productPictures) {
                String fileName = getUniqueUploadFileName(Objects.requireNonNull(pic.getOriginalFilename()));

                pic.transferTo(new File("D:/Future/JavaWeb/Upload/Product/images/" + fileName));

                BookImages bookImages = new BookImages();
                bookImages.setPath("Product/images/" + fileName);
                bookImages.setTitle(fileName);

                book.addBookImages(bookImages);
            }
        }
        return saveOrUpdate(book);

    }
    @Transactional
    public Book updateProduct(Book p, MultipartFile Avatar, MultipartFile[] productPictures)
            throws IllegalStateException, IOException {

        Book bookInDb = bookRepository.getById(p.getId());

        if (isEmptyUploadFile(Avatar)) {
            new File("C:/upload/" + bookInDb.getAvatar()).delete();

            String fileName = getUniqueUploadFileName(Objects.requireNonNull(Avatar.getOriginalFilename()));
            Avatar.transferTo(new File("D:/Future/JavaWeb/Upload/Product/avatar/" + fileName));
            p.setAvatar("Product/avatar/" + fileName);
        } else {
            p.setAvatar(bookInDb.getAvatar());
        }

        if (isEmptyUploadFile(productPictures)) {
            if (bookInDb.getBookList() != null && !bookInDb.getBookList().isEmpty()) {
                for (BookImages opi : bookInDb.getBookList()) {
                    new File("D:/Future/JavaWeb/Upload/" + opi.getPath()).delete();
                    bookImagesService.delete(opi);
                }
            }
            for (MultipartFile pic : productPictures) {
                String fileName = getUniqueUploadFileName(Objects.requireNonNull(pic.getOriginalFilename()));

                pic.transferTo(new File("D:/Future/JavaWeb/Upload/Product/images/" + fileName));

                BookImages pi = new BookImages();
                pi.setPath("Product/images/" + fileName);
                pi.setTitle(fileName);

                p.addBookImages(pi);
            }
        }
        return saveOrUpdate(p);
    }
}
