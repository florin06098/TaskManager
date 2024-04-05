package fs.todo_project.controller;

import fs.todo_project.config.UserDetailsExtension;
import fs.todo_project.entity.Task;
import fs.todo_project.entity.User;
import fs.todo_project.handler.TaskNotFoundException;
import fs.todo_project.service.JwtService;
import fs.todo_project.service.TaskService;
import fs.todo_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskRestController {
    private final JwtService jwtService;
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable int id){
        Optional<Task> task = taskService.getTask(id);
        if(task.isEmpty()){
            throw new TaskNotFoundException("The task with id: " + id + " does not exist");
        }
        return task.get();
    }

    @GetMapping("/tasks")
    public List<Task> getTaskList(){
        return taskService.getTasks();
    }

    @PostMapping("/task")
    public Task createTaskForUser(@RequestBody Task theTask, Authentication authentication){
        UserDetailsExtension principal = (UserDetailsExtension) authentication.getPrincipal();
        User user = principal.getUser();
        theTask.setId(0);
        user.getTasks().add(theTask);
        userService.save(user);
        return theTask;
    }

    @PutMapping("/task")
    public Task updateTask(@RequestBody Task theTask){
        if(taskService.getTask(theTask.getId()).isPresent()){
            return taskService.save(theTask);
        } else{
            throw new TaskNotFoundException("Could not update the task: " + theTask.getName() + " because it does not exist");
        }
    }

    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable int id){
          taskService.deleteTask(id);
    }
}
