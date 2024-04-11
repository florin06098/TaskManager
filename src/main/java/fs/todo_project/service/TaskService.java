package fs.todo_project.service;

import fs.todo_project.model.Task;
import fs.todo_project.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task getTask(Integer id);
    List<Task> getUserTasks(Integer id);
    List<Task> getTasks();
    Task updateTask(Task task);
    void deleteTask(Integer id, User user);
    Task createTask(Task task, User user);
}
