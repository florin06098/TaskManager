package fs.todo_project.service;

import fs.todo_project.entity.AuthRequest;
import fs.todo_project.entity.UserInfo;

public interface UserInfoService {
    UserInfo registerUser(AuthRequest userRegistrationRequest);
    UserInfo updateUser(AuthRequest userRegistrationRequest);
    String deleteUser(int id);
}
