package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.User;
import fit.se2.APlusBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserService{

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("No User with username %s found", username));
        }
        User temp = user.get();
        return new org.springframework.security.core.userdetails.User(temp.getUserName(), temp.getPassword());
    }
}
