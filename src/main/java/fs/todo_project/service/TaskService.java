package fs.todo_project.service;

import fs.todo_project.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task getTask(Integer id);
    List<Task> getTasks();
    Task updateTask(Task task);
    void deleteTask(Integer id);
}
