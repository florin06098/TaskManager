package fs.todo_project.config;
import fs.todo_project.repository.UserRepository;
import fs.todo_project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceExtension implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Entering loadUserByUsername() method");
        Optional<User> userInfo = repository.findByName(username);
        System.out.println("userInfo: " + userInfo);

        return userInfo.map(UserDetailsExtension::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }

}