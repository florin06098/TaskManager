package fs.todo_project.rest;

import fs.todo_project.entity.Task;
import fs.todo_project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {
    TaskService taskService;

    @Autowired
    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task/{id}")
    private Task getTask(@PathVariable int id){
        Task task = taskService.getTask(id);
        if(task == null){
            throw new TaskNotFoundException("The task with id: " + id + " does not exist");
        }
        return task;
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
        return taskService.save(theTodo);
    }

    @DeleteMapping("/task/{id}")
    private void deleteTask(@PathVariable int id){
          taskService.deleteTask(id);
    }


}
