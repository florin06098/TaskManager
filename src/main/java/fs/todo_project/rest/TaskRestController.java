package fs.todo_project.rest;

import fs.todo_project.entity.AuthRequest;
import fs.todo_project.entity.Task;
import fs.todo_project.handler.TaskNotFoundException;
import fs.todo_project.service.JwtService;
import fs.todo_project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task/{id}")
    private Task getTask(@PathVariable int id){
        Optional<Task> task = taskService.getTask(id);
        if(task.isEmpty()){
            throw new TaskNotFoundException("The task with id: " + id + " does not exist");
        }
        return task.get();
    }

    @GetMapping("/tasks")
    private List<Task> getTaskList(){
        return taskService.getTasks();
    }

    @PostMapping("/task")
    private Task createTask(@RequestBody Task theTodo){
        theTodo.setId(0);
        return taskService.save(theTodo);
    }

    @PutMapping("/task")
    private Task updateTask(@RequestBody Task theTodo){
        if(taskService.getTask(theTodo.getId()).isPresent()){
            return taskService.save(theTodo);
        } else{
            throw new TaskNotFoundException("Could not update the task: " + theTodo.getName() + " because it does not exist");
        }
    }

    @DeleteMapping("/task/{id}")
    private void deleteTask(@PathVariable int id){
          taskService.deleteTask(id);
    }

}
