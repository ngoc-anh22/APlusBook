package fit.se2.APlusBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.se2.APlusBook.model.Comment;

public interface CommentRepository extends JpaRepository <Comment, Long> {
    
}
