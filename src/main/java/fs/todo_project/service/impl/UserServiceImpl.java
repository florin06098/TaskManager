package fs.todo_project.service.impl;

import fs.todo_project.handler.UserAlreadyExists;
import fs.todo_project.model.*;
import fs.todo_project.repository.UserRepository;
import fs.todo_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(AuthRequest userRegistrationRequest) {
        Optional<User> userFound = userRepository.findByName(userRegistrationRequest.getUsername());
        if (userFound.isPresent()) {
            throw new UserAlreadyExists("User with username " + userRegistrationRequest.getUsername() + " already exists");
        }

        User user = new User();
        user.setId(0);
        user.setRoles("ROLE_USER");
        user.setName(userRegistrationRequest.getUsername());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        user.setTasks(new ArrayList<>());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(AuthRequest authRequest) {
        User user = userRepository.findByName(authRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("User with username " + authRequest.getUsername() + " does not exist"));
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "Succesfully deleted the user";
        } else {
            return "Could not find the user with id: " + id;
        }
    }

    @Override
    public Optional<User> findByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public User createTask(Task task, User user) {
//        task.setId(0);
//        task.setTaskStatus(TaskStatus.TODO);
//        task.setTaskPriority(TaskPriority.HIGH);
//        user.getTasks().add(task);
//        return userRepository.save(user);
//    }


}
