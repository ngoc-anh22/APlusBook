package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.Publisher;
import org.springframework.stereotype.Service;

@Service
public class PublisherService extends BaseService<Publisher> {
    @Override
    protected Class<Publisher> clazz() {
        return Publisher.class;
    }
}
