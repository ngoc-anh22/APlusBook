package fit.se2.APlusBook.repository;

import fit.se2.APlusBook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
    List<User> findByPhoneNumber(String phoneNumber);
    List<User> findByEmail(String email);
}
