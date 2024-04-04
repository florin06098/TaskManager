package fs.todo_project.rest;

import fs.todo_project.entity.AuthRequest;
import fs.todo_project.entity.UserInfo;
import fs.todo_project.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/register")
    public String registerNewUser(@Valid @RequestBody AuthRequest authRequest){
        System.out.println("Entered register user");
        UserInfo registeredUser = userInfoService.registerUser(authRequest);
        return registeredUser != null ? "Successfuly registered the user" : "User already exists";
    }
}
