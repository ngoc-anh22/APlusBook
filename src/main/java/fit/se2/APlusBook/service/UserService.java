package fit.se2.APlusBook.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService extends BaseService<User> {

    @Override
    protected Class<User> clazz() {
        return User.class;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "select * from tbl_users u where u.username = '" + username + "' and status = 1";
        return this.getEntityByNativeSQL(sql);
    }
}
