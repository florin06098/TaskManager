package fs.todo_project.controller;

import fs.todo_project.model.AuthRequest;
import fs.todo_project.model.User;
import fs.todo_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String registerNewUser(@RequestBody AuthRequest authRequest){
        User registeredUser = userService.registerUser(authRequest);
        return registeredUser != null ? "Successfully registered the user" : "User already exists";
    }

    @PutMapping("/update")
    public User updateExistingUser(@RequestBody AuthRequest authRequest){
        return userService.updateUser(authRequest);
    }

    @DeleteMapping("/remove/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String removeUser(@PathVariable Integer userId){
        String response = userService.deleteUser(userId);
        return response;
    }

}
