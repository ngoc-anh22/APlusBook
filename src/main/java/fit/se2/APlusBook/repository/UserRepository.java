package fit.se2.APlusBook.repository;

import fit.se2.APlusBook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

=======
    List<User> findByUserName(String userName);
    List<User> findByPhoneNum(String phoneNum);
    List<User> findByEmail(String email);
>>>>>>> Stashed changes
}
