package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category>{
    protected Class<Category> clazz() {
        return Category.class;
    }
}
