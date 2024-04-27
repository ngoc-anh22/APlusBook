package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends BaseService<Comment> {
    @Override
    protected Class<Comment> clazz() {
        return Comment.class;
    }
}
