package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.User;
import fit.se2.APlusBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class UserService{

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("No User with username %s found", username));
        }
        User user1 = user.getFirst();
        return new org.springframework.security.core.userdetails.User(user1.getUserName(), user1.getPassword(), user1.getAuthorities());
    }
}
