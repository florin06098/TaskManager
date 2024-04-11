package fs.todo_project.service;

import fs.todo_project.model.AuthRequest;
import fs.todo_project.model.Task;
import fs.todo_project.model.User;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserService {
    User registerUser(AuthRequest userRegistrationRequest);
    User updateUser(AuthRequest userRegistrationRequest);
    String deleteUser(Integer id);
    Optional<User> findByName(@Param("username") String username);
    User save(User user);
//    User createTask(Task task, User user);
}
