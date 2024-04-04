package fs.todo_project.config;
import fs.todo_project.config.UserInfoUserDetails;
import fs.todo_project.dao.UserInfoRepository;
import fs.todo_project.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Entering loadUserByUsername() method");
        Optional<UserInfo> userInfo = findUserByUsername(username);
        System.out.println("userInfo: " + userInfo);

        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }

    public Optional<UserInfo> findUserByUsername(String username){
        return repository.findByName(username);
    }

    public UserInfo register(UserInfo theUserInfo){
        theUserInfo.setId(0);
        UserInfo userInfo = repository.save(theUserInfo);
        return userInfo;
    }

    public UserInfo update(UserInfo theUserInfo){
        UserInfo userInfo = repository.save(theUserInfo);
        return userInfo;
    }
}