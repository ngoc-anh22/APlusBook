package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.BookImages;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookImagesService {

    private EntityManager entityManager;

    @Transactional
    public void delete(BookImages entity) {
        entityManager.remove(entity);
    }
}
