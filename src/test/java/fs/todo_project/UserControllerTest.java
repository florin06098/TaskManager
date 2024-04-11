package fs.todo_project;

import fs.todo_project.controller.UserController;
import fs.todo_project.model.AuthRequest;
import fs.todo_project.model.User;
import fs.todo_project.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController underTest;


    @Test
    void shouldRegisterNewUser() {
        //given
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("email");
        authRequest.setPassword("pass");
        authRequest.setUsername("user");

        // when
        ResponseEntity<User> userResponseEntity = underTest.registerNewUser(authRequest);

        // then
        verify(userService).registerUser(authRequest);
        assertEquals(HttpStatus.CREATED, userResponseEntity.getStatusCode());
    }

    @Test
    void shouldUpdateANewUser() {
        //given
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("mail");
        authRequest.setPassword("elo2");
        authRequest.setUsername("florin");

        User expectedUser = new User();

        // When
        when(userService.updateUser(authRequest)).thenReturn(expectedUser);
        ResponseEntity<User> userResponseEntity = underTest.updateExistingUser(authRequest);

        // Then
        verify(userService).updateUser(authRequest);
        assertEquals(HttpStatus.OK, userResponseEntity.getStatusCode());
        assertEquals(expectedUser, userResponseEntity.getBody());
    }
}
