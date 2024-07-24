package fs.todo_project.controller;

import fs.todo_project.config.UserDetailsExtension;
import fs.todo_project.model.Task;
import fs.todo_project.model.User;
import fs.todo_project.service.impl.JwtService;
import fs.todo_project.service.TaskService;
import fs.todo_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskRestController {
    private final TaskService taskService;

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id){
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }

    @GetMapping("/userTasks")
    public ResponseEntity<List<User>> getUserTasks(Authentication authentication){
        System.out.println("getUserTasks called");
        UserDetailsExtension userDetails = (UserDetailsExtension) authentication.getPrincipal();
        return new ResponseEntity<>(taskService.getUserTasks(userDetails), HttpStatus.OK);
    }

    @GetMapping("/tasks")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Task>> getTaskList(){
        return new ResponseEntity<>(taskService.getTasks(), HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTaskForUser(@RequestBody Task theTask, Authentication authentication){
        UserDetailsExtension principal = (UserDetailsExtension) authentication.getPrincipal();
        User user = principal.getUser();
        return new ResponseEntity<>(taskService.createTask(theTask, user), HttpStatus.OK);
    }

    @PutMapping("/task")
    public ResponseEntity<Task> updateTask(@RequestBody Task theTask){
        return new ResponseEntity<>(taskService.updateTask(theTask), HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id, Authentication authentication){
        UserDetailsExtension principal = (UserDetailsExtension) authentication.getPrincipal();
        User user = principal.getUser();
        taskService.deleteTask(id, user);
        return ResponseEntity.ok().build();
    }


}
