package fs.todo_project.controller;

import fs.todo_project.model.AuthRequest;
import fs.todo_project.model.User;
import fs.todo_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerNewUser(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<>(userService.registerUser(authRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateExistingUser(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<>(userService.updateUser(authRequest), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> removeUser(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

}
