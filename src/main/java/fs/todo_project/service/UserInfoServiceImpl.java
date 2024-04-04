package fs.todo_project.service;

import fs.todo_project.dao.UserInfoRepository;
import fs.todo_project.entity.AuthRequest;
import fs.todo_project.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfo registerUser(AuthRequest userRegistrationRequest) {
        Optional<UserInfo> userFound = userInfoRepository.findByName(userRegistrationRequest.getUsername());
        if (userFound.isPresent()) {
            throw new IllegalArgumentException("User with username " + userRegistrationRequest.getUsername() + " already exists");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setId(0);
        userInfo.setRoles("USER");
        userInfo.setName(userRegistrationRequest.getUsername());
        userInfo.setEmail(userRegistrationRequest.getEmail());
        userInfo.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo updateUser(AuthRequest authRequest) {
        Optional<UserInfo> userFound = userInfoRepository.findByName(authRequest.getUsername());
        if (userFound.isEmpty()) {
            throw new IllegalArgumentException("User with username " + authRequest.getUsername() + " does not exist");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setName(authRequest.getUsername());
        userInfo.setEmail(authRequest.getEmail());
        userInfo.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        return userInfoRepository.save(userInfo);
    }

    @Override
    public String deleteUser(int id) {
        Optional<UserInfo> userFound = userInfoRepository.findById(id);
        if (userFound.isPresent()) {
            userInfoRepository.delete(userFound.get());
            return "Succesfully deleted the user";
        } else {
            return "Could not find the user with id: " + id;
        }
    }


}
