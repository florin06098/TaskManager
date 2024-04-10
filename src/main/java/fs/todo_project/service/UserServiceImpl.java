package fs.todo_project.service;

import fs.todo_project.handler.UserAlreadyExists;
import fs.todo_project.repository.UserRepository;
import fs.todo_project.model.AuthRequest;
import fs.todo_project.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//        When you update you should keep the List<Task> exactly the same, giving only the possibility to modify the username, the email and the password
        Optional<User> userFound = userRepository.findByName(authRequest.getUsername());
        if (userFound.isEmpty()) {
            throw new IllegalArgumentException("User with username " + authRequest.getUsername() + " does not exist");
        }
        User user = new User();
        user.setName(authRequest.getUsername());
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(int id) {
        if (userRepository.existsById(id)) {
            System.out.println("It exists");
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


}
