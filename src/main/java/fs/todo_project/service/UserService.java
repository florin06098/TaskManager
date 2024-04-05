package fs.todo_project.service;

import fs.todo_project.entity.AuthRequest;
import fs.todo_project.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserService {
    User registerUser(AuthRequest userRegistrationRequest);
    User updateUser(AuthRequest userRegistrationRequest);
    String deleteUser(int id);
    Optional<User> findByName(@Param("username") String username);
    User save(User user);
}
