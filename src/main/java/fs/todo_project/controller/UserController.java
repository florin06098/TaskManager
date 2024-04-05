package fs.todo_project.controller;

import fs.todo_project.entity.AuthRequest;
import fs.todo_project.entity.User;
import fs.todo_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerNewUser(@RequestBody AuthRequest authRequest){
        User registeredUser = userService.registerUser(authRequest);
        return registeredUser != null ? "Successfuly registered the user" : "User already exists";
    }

    @PutMapping("/update")
    public User updateExistingUser(@RequestBody AuthRequest authRequest){
        return userService.updateUser(authRequest);
    }

    @DeleteMapping("/remove/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String removeUser(@PathVariable int userId){
        String response = userService.deleteUser(userId);
        return response;
    }

}
